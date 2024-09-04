package project.study_with_me.domain.study.dto;

import lombok.Getter;

@Getter
public class StudyInfo {

    private String studyImage;
    private String title;
    private String type;    // 온, 오프라인
    private String recruitPeople; // 모집 인원
    private String introduction;    // 소개
    private String topic;   // 주제
    private String difficulty;  // 난이도
    private String tag; // 검색 태그
}
