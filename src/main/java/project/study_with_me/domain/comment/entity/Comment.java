package project.study_with_me.domain.comment.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content")
    private String content;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "study_id")
    private Long studyId;

    @Column(name = "anonymous")
    private Boolean anonymous;  // 익명

    @Column(name = "comment_date_time")
    private LocalDateTime commentDateTime;

}
