package project.study_with_me.domain.comment.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.study_with_me.domain.comment.entity.Comment;
import project.study_with_me.domain.comment.repository.CommentRepository;

import static project.study_with_me.text.CommentTexts.NO_SEARCH_COMMENT;

@Component
@RequiredArgsConstructor
public class CommentUtil {

    private final CommentRepository commentRepository;

    public Comment findComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException(NO_SEARCH_COMMENT.getText()));
    }
}
