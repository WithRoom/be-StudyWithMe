package project.study_with_me.auth.text;

import lombok.Getter;

@Getter
public enum KakaoTexts {
    GRANT_TYPE("authorization_code");

    private String text;

    KakaoTexts(String text) {
        this.text = text;
    }
}
