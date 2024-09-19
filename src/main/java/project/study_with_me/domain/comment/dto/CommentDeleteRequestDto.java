package project.study_with_me.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "댓글 삭제 Request")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteRequestDto {

    private Long commentId;
}
