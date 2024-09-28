package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyDetail {

    private Long studyId;
    private String studyImageUrl;
    private String title;
    private String topic;
    private String difficulty;
    private String tag;
    private Boolean state;
    private String introduction;
    private Integer recruitPeople;
    private Boolean finish;
}
