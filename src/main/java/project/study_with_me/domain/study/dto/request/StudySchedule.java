package project.study_with_me.domain.study.dto.request;

import lombok.Getter;

@Getter
public class StudySchedule {

    private String weekDay; // 요일
    private String startDay;
    private String period;  // 기간
    private String time;
}
