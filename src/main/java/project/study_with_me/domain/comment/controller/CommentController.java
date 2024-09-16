package project.study_with_me.domain.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.domain.comment.dto.CommentInfoRequestDto;
import project.study_with_me.domain.comment.dto.CommentRequestDto;
import project.study_with_me.domain.comment.dto.CommentResponseDto;
import project.study_with_me.domain.comment.service.CommentService;

@Tag(name = "Comment", description = "댓글 생성 및 조회")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성")
    @PostMapping("/create")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDto requestDto) {
        return ResponseEntity.ok(commentService.createComment(requestDto));
    }

/*    @Operation(summary = "댓글 조회")
    @PostMapping("/info")
    public ResponseEntity<CommentResponseDto> commentInfo(@RequestBody CommentInfoRequestDto requestDto) {
        return ResponseEntity.ok(commentService.commentInfo(requestDto));
    }*/
}
