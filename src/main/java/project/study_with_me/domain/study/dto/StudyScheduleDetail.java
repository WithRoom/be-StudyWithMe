package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyScheduleDetail {

    private Boolean state;
    private String startDay;
    private String weekDay;
    private String time;
    private Integer nowPeople;
    private Integer recruitPeople;
}
