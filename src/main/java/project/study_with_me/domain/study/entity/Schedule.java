package project.study_with_me.domain.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String startDay;

    @Column(name = "period")
    private String period;  // 기간

    @Column(name = "time")
    private String time;

    @OneToOne(mappedBy = "schedule")
    private Study study;

    public void setStudy(Study study) {
        this.study = study;
    }
}
