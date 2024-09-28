package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudySchedule {

    private String weekDay; // 요일
    private String startDay;
    private LocalDate endDay;
    private String period;  // 기간
    private String time;
}
