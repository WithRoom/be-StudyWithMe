package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum CommentTexts {

    NO_SEARCH_COMMENT("해당 댓글이 없습니다.");

    private String text;

    CommentTexts(String text) {
        this.text = text;
    }
}
