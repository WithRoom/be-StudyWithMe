package project.study_with_me.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private String accessTokenExpireTime;
    private String refreshToken;

    public KakaoLoginResponseDto createKakaoLoginResponseDto() {
        return KakaoLoginResponseDto.builder()
                .accessToken(accessToken)
                .expireTime(accessTokenExpireTime)
                .build();
    }
}