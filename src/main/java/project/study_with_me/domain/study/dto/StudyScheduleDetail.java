package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyScheduleDetail {

    private Boolean state;
    private String startDay;
    private LocalDate endDay;
    private String weekDay;
    private String time;
    private Integer nowPeople;
    private Integer recruitPeople;
    private String period;  // 기간
}
