package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum MemberTexts {

    NO_SEARCH_MEMBER("해당 회원이 없습니다.");

    private String text;

    MemberTexts(String text) {
        this.text = text;
    }
}
