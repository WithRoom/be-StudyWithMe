package project.study_with_me.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeStudyInfo {

    private Long studyId;
    private Boolean state;
    private String type;
    private String studyImageUrl;
    private String title;
    private String topic;
    private String difficulty;
    private Integer nowPeople;
    private Boolean finish;
    private Boolean interest;
    private Integer recruitPeople;
}
