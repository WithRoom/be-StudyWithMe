package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ResponseSignUpStudy {

    private Long studyId;
    private Boolean state;  // 진행 중, 종료됨
    private String type;    // 온, 오프라인
    private String studyImageUrl;
    private String topic;   // 주제
    private String difficulty;  // 난이도
    private String title;
    private Integer recruitPeople;   // 참여인원
    private Integer nowPeople;  // 현재인원
    private Boolean interest;
    private Long memberId;
}