package project.study_with_me.auth.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.auth.dto.response.KakaoLoginResponseDto;
import project.study_with_me.auth.dto.TokenDto;
import project.study_with_me.auth.entity.RefreshToken;
import project.study_with_me.auth.jwt.JwtProvider;
import project.study_with_me.auth.repository.RefreshTokenRepository;
import project.study_with_me.domain.member.entity.Member;

import static project.study_with_me.text.KakaoTexts.BEARER;

@RequiredArgsConstructor
@Component
public class LoginUtils {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * 인증 정보 생성
     */
    public Authentication createAuthentication(Member member) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member.getEmail(), member.getMemberId());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    /**
     * 토큰 생성
     */
    @Transactional
    public KakaoLoginResponseDto getToken(Authentication authentication, Boolean firstJoin) {
        // 인증 정보를 기반으로 JWT 생성
        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .refreshTokenId(Long.valueOf(authentication.getName()))
                .refreshToken(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        return tokenDto.createKakaoLoginResponseDto(BEARER.getText(), firstJoin);
    }
}
