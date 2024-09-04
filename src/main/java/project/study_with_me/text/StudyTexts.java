package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum StudyTexts {
    SUCCESS_CREATE_STUDY("success Create Study");

    private String text;

    StudyTexts(String text) {
        this.text = text;
    }
}
