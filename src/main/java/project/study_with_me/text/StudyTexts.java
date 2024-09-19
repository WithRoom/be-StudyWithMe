package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum StudyTexts {

    NO_SEARCH_STUDY("해당 스터디가 없습니다."),
    NO_SEARCH_JOIN_STUDY("해당 스터디에 참여 요청이 없습니다.");

    private String text;

    StudyTexts(String text) {
        this.text = text;
    }
}
