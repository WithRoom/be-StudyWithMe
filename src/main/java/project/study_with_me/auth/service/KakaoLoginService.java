package project.study_with_me.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.auth.client.KakaoTokenClient;
import project.study_with_me.auth.client.KakaoUserClient;
import project.study_with_me.auth.dto.KakaoLoginResponseDto;
import project.study_with_me.auth.dto.KakaoTokenResponseDto;
import project.study_with_me.auth.dto.KakaoUserResponseDto;
import project.study_with_me.auth.dto.TokenDto;
import project.study_with_me.auth.entity.RefreshToken;
import project.study_with_me.auth.jwt.JwtProvider;
import project.study_with_me.auth.repository.RefreshTokenRepository;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;

import static project.study_with_me.auth.text.KakaoTexts.BEARER;
import static project.study_with_me.auth.text.KakaoTexts.GRANT_TYPE;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginService {

    private final MemberRepository memberRepository;
    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoUserClient kakaoUserClient;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${kakao.client_id}") 
    private String clientId;

    /**
     * Kakao 에서 받은 code 를 사용해 Token 데이터 받기
     */
    public KakaoLoginResponseDto getAccessTokenFromKakao(String code) {
        KakaoTokenResponseDto responseDto = kakaoTokenClient.getToken(
                GRANT_TYPE.getText(),
                clientId,
                code
        );

        return generateOAuthTokenResponse(responseDto);
    }

    /**
     * Kakao User API 를 호출해 User Info 조회
     */
    @Transactional
    public KakaoLoginResponseDto generateOAuthTokenResponse(KakaoTokenResponseDto responseDto) {
        String authorizationHeader = BEARER.getText() + responseDto.getAccessToken();
        KakaoUserResponseDto userInfo = kakaoUserClient.getUserInfo(authorizationHeader);

        // Member 조회
        return memberRepository.findById(Long.valueOf(userInfo.getId()))
                .map(member -> {    /** 기존 회원 */
                    // email 을 기반으로 Authentication 생성, authentication.getName() 은 MemberId
                    // CustomerUserDetailSsService 에서 MemberId 가 들어가도록 설정
                    Authentication authentication = createAuthentication(member);

                    // 인증 정보를 기반으로 TokenDto 생성 및 RefreshToken 저장
                    KakaoLoginResponseDto kakaoLoginResponseDto = getToken(authentication);

                    System.out.println(kakaoLoginResponseDto.getAccessToken());

                    return kakaoLoginResponseDto;
                })
                .orElseGet(() -> {  /** 신규 회원 */
                    Member member = userInfo.createMember(passwordEncoder);
                    memberRepository.save(member);

                    Authentication authentication = createAuthentication(member);

                    KakaoLoginResponseDto kakaoLoginResponseDto = getToken(authentication);

                    System.out.println(kakaoLoginResponseDto.getAccessToken());

                    return kakaoLoginResponseDto;
                });
    }

    private Authentication createAuthentication(Member member) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member.getEmail(), member.getMemberId());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @Transactional
    private KakaoLoginResponseDto getToken(Authentication authentication) {
        // 인증 정보를 기반으로 JWT 생성
        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .refreshTokenId(Long.valueOf(authentication.getName()))
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto.createKakaoLoginResponseDto();
    }
}
