package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum KakaoTexts {

    BEARER("Bearer"),
    GRANT_TYPE("authorization_code"),
    NO_REFRESHTOKEN("해당 유저의 refreshToken 이 없습니다."),
    WRONG_ACCESSTOKEN("accessToken 이 유효하지 않습니다.");

    private String text;

    KakaoTexts(String text) {
        this.text = text;
    }
}
