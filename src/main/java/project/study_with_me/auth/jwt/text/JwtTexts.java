package project.study_with_me.auth.jwt.text;

import lombok.Getter;

@Getter
public enum JwtTexts {

    FALSE("false"),
    TRUE("true"),
    ERROR("error"),
    INVALID_JWT("무효한 JWT 토큰입니다."),
    EXPIRED_JWT("만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT("지원하지 않는 JWT 토큰입니다."),
    WRONG_JWT("잘못된 JWT 토큰입니다.");

    private String text;

    JwtTexts(String text) {
        this.text = text;
    }
}
