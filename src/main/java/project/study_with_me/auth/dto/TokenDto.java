package project.study_with_me.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import project.study_with_me.auth.dto.response.KakaoLoginResponseDto;

@Getter
@AllArgsConstructor
@Builder
public class TokenDto {

    private String grantType;
    private String accessToken;
    private String accessTokenExpireTime;
    private String refreshToken;

    public KakaoLoginResponseDto createKakaoLoginResponseDto(String grantType, Boolean firstJoin) {
        return KakaoLoginResponseDto.builder()
                .grantType(grantType)
                .accessToken(accessToken)
                .expireTime(accessTokenExpireTime)
                .firstJoin(firstJoin)
                .build();
    }
}
