package project.study_with_me.domain.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {

    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;    // studyId

    @Column(name = "week_day")
    private String weekDay; // 요일

    @Column(name = "start_day")
    private String startDay;    // 신청 및 스터디 생성 시작일

    @Column(name = "end_day")
    private LocalDate endDay;   // 신청 마감일

    @Column(name = "period")
    private String period;  // 기간

    @Column(name = "time")
    private String time;

    @Column(name = "state")
    private Boolean state;  // 진행 중 or 종료됨

    @OneToOne(mappedBy = "schedule")
    private Study study;

    public void setStudy(Study study) {
        this.study = study;
    }
}
