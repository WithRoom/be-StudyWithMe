package project.study_with_me.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.comment.entity.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    private Long studyId;
    private String content;

    public Comment createComment() {
        return Comment.builder()
                .content(content)
                .studyId(studyId)
                .memberId(SecurityUtil.getCurrentMemberId())
                .build();
    }
}