package project.study_with_me.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.auth.client.KakaoTokenClient;
import project.study_with_me.auth.client.KakaoUserClient;
import project.study_with_me.auth.dto.*;
import project.study_with_me.auth.dto.request.KakaoLoginReissueDto;
import project.study_with_me.auth.dto.response.KakaoLoginResponseDto;
import project.study_with_me.auth.dto.response.KakaoTokenResponseDto;
import project.study_with_me.auth.dto.response.KakaoUserResponseDto;
import project.study_with_me.auth.dto.response.LoginStateResponseDto;
import project.study_with_me.auth.entity.RefreshToken;
import project.study_with_me.auth.jwt.JwtProvider;
import project.study_with_me.auth.repository.RefreshTokenRepository;
import project.study_with_me.auth.util.LoginUtils;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;

import static project.study_with_me.auth.jwt.text.JwtTexts.ERROR;
import static project.study_with_me.auth.jwt.text.JwtTexts.FALSE;
import static project.study_with_me.text.KakaoTexts.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginService {

    private final MemberRepository memberRepository;
    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoUserClient kakaoUserClient;
    private final PasswordEncoder passwordEncoder;
    private final LoginUtils loginUtils;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

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
     * Kakao OAuth Logout
     */
    @Transactional
    public String kakaoOAuthLogout(Long memberId) {

        RefreshToken refreshToken = refreshTokenRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException(NO_REFRESHTOKEN.getText()));

        refreshTokenRepository.delete(refreshToken);

        SecurityContextHolder.clearContext();   // 사용자 정보 삭제

        return LOGOUT.getText();
    }

    /**
     * 토큰 만료 후 토큰 재발급
     */
    public KakaoLoginResponseDto reissue(KakaoLoginReissueDto kakaoLoginReissueDto) {

        // accessToken 이 만료되지 않은 경우
        if (jwtProvider.checkExpireToken(kakaoLoginReissueDto.getAccessToken()).equals(FALSE.getText())) {
            return kakaoLoginReissueDto.createKakaoLoginResponseDto();

        // accessToken 이 올바르지 않은 경우
        } else if (jwtProvider.checkExpireToken(kakaoLoginReissueDto.getAccessToken()).equals(ERROR.getText())) {
            throw new RuntimeException(WRONG_ACCESSTOKEN.getText());

        // accessToken 이 만료되지 않은 경우
        } else {
            Authentication authentication = jwtProvider.getAuthentication(kakaoLoginReissueDto.getAccessToken());

            RefreshToken refreshToken = refreshTokenRepository.findById(Long.valueOf(authentication.getName()))
                    .orElseThrow(() -> new RuntimeException(NO_REFRESHTOKEN.getText()));

            // Token 생성
            TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);

            // refreshToken Update
            refreshToken.updateRefreshToken(tokenDto.getRefreshToken());

            return tokenDto.createKakaoLoginResponseDto(BEARER.getText(), false);
        }
    }

    /**
     * Kakao User API 를 호출해 User Info 조회
     */
    @Transactional
    public KakaoLoginResponseDto generateOAuthTokenResponse(KakaoTokenResponseDto responseDto) {
        String authorizationHeader = BEARER.getText() + " " + responseDto.getAccessToken();
        KakaoUserResponseDto userInfo = kakaoUserClient.getUserInfo(authorizationHeader);

        // Member 조회
        return memberRepository.findById(Long.valueOf(userInfo.getId()))
                .map(member -> {    /** 기존 회원 */
                    // email 을 기반으로 Authentication 생성, authentication.getName() 은 MemberId
                    // CustomerUserDetailSsService 에서 MemberId 가 들어가도록 설정
                    Authentication authentication = loginUtils.createAuthentication(member);

                    // 인증 정보를 기반으로 TokenDto 생성 및 RefreshToken 저장
                    KakaoLoginResponseDto kakaoLoginResponseDto = loginUtils.getToken(authentication, false);

                    return kakaoLoginResponseDto;
                })
                .orElseGet(() -> {  /** 신규 회원 */
                    Member member = userInfo.createMember(passwordEncoder);
                    memberRepository.save(member);

                    Authentication authentication = loginUtils.createAuthentication(member);

                    KakaoLoginResponseDto kakaoLoginResponseDto = loginUtils.getToken(authentication, true);

                    return kakaoLoginResponseDto;
                });
    }

    public LoginStateResponseDto getLoginState(Long memberId) {
        if (memberId == null) {
            return new LoginStateResponseDto(false);
        } else {
            return new LoginStateResponseDto(true);
        }
    }
}
