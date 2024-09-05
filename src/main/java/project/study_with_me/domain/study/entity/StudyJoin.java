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
@Table(name = "study_join")
public class StudyJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studyJoinId;

    @Column(name = "join_member_id")
    private Long joinMemberId;

    @Column(name = "group_leader_id")
    private Long groupLeaderId;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "state")
    private Boolean state;
}
