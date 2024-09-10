package project.study_with_me.auth.dto.request;

import lombok.Getter;
import project.study_with_me.auth.dto.response.KakaoLoginResponseDto;

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
