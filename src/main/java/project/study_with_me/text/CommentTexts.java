package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum CommentTexts {
    COMMENT_CREATE("comment Create Success");

    private String text;

    CommentTexts(String text) {
        this.text = text;
    }
}
