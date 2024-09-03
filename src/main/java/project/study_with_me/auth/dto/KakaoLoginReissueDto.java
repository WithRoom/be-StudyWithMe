package project.study_with_me.auth.dto;

import lombok.Getter;

@Getter
public class KakaoLoginReissueDto {

    private String grantType;
    private String accessToken;
    private String expireTime;

    public KakaoLoginResponseDto createKakaoLoginResponseDto() {
        return KakaoLoginResponseDto.builder()
                .grantType(grantType)
                .accessToken(accessToken)
                .expireTime(expireTime)
                .build();
    }
}
