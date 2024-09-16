package project.study_with_me.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.comment.dto.CommentRequestDto;
import project.study_with_me.domain.comment.entity.Comment;
import project.study_with_me.domain.comment.repository.CommentRepository;

import static project.study_with_me.text.CommentTexts.COMMENT_CREATE;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public String createComment(CommentRequestDto requestDto) {
        Comment comment = requestDto.createComment();
        commentRepository.save(comment);

        return COMMENT_CREATE.getText();
    }

}
