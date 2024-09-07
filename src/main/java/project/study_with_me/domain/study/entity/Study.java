package project.study_with_me.domain.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "study")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "group_leader")
    private Long groupLeader;

    @Column(name = "study_image_url")
    private String studyImageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;    // 온, 오프라인

    @Column(name = "recruit_people")
    private Integer recruitPeople; // 모집 인원

    @Column(name = "introduction")
    private String introduction;    // 소개

    @Column(name = "topic")
    private String topic;   // 주제

    @Column(name = "difficulty")
    private String difficulty;  // 난이도

    @Column(name = "tag")
    private String tag; // 검색 태그

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
    private Schedule schedule;

    // 연관관계 편의 메소드
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
