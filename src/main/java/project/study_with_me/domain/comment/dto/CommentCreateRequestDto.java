package project.study_with_me.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.comment.entity.Comment;

@Schema(name = "댓글 생성 Request")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {

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
