package project.study_with_me.domain.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "study_interest")
public class StudyInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyInterestId;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "study_id")
    private Long studyId;
}
