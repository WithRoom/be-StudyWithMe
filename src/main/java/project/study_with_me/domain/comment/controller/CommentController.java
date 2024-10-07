package project.study_with_me.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.comment.dto.CommentCreateRequestDto;
import project.study_with_me.domain.comment.dto.CommentDeleteRequestDto;
import project.study_with_me.domain.comment.service.CommentService;

@Tag(name = "Comment", description = "댓글 생성 및 삭제")
@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성")
    @PostMapping("/create")
    public ResponseEntity<Boolean> createComment(@RequestBody CommentCreateRequestDto requestDto) {
        return ResponseEntity.ok(commentService.createComment(requestDto));
    }

    @Operation(summary = "댓글 삭제")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteComment(@RequestBody CommentDeleteRequestDto requestDto) {
        return ResponseEntity.ok(commentService.deleteComment(requestDto, SecurityUtil.getCurrentMemberId()));
    }

}
