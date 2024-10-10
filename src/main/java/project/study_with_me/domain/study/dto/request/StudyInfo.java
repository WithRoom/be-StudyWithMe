package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudyInfo {

    private String studyImageUrl;
    private String title;
    private String type;    // 온, 오프라인
    private Integer recruitPeople; // 모집 인원
    private String introduction;    // 소개
    private String topic;   // 주제
    private String difficulty;  // 난이도
    private String tag; // 검색 태그
    private String kakaoOpenChatUrl;
}
