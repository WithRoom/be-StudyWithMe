package project.study_with_me.text;

import lombok.Getter;

@Getter
public enum StudyTexts {

    NO_SEARCH_STUDY("해당 스터디가 없습니다."),
    SUCCESS_CREATE_STUDY("success Create Study"),
    SUCCESS_INTEREST_STUDY("success Interest Study"),
    SUCCESS_JOIN_STUDY("success Join Study"),
    GROUP_LEADER("해당 유저는 그룹장입니다."),
    SUCCESS_JOIN_RESPONSE_STUDY("success Join Response Study");

    private String text;

    StudyTexts(String text) {
        this.text = text;
    }
}
