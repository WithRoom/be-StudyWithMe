package project.study_with_me.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoLoginResponseDto {

    private String grantType;
    private String accessToken;
    private String expireTime;
    private Boolean firstJoin; // 회원가입유무
}
