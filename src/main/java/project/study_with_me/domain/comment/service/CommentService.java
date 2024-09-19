package project.study_with_me.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.comment.dto.CommentCreateRequestDto;
import project.study_with_me.domain.comment.dto.CommentDeleteRequestDto;
import project.study_with_me.domain.comment.entity.Comment;
import project.study_with_me.domain.comment.repository.CommentRepository;
import project.study_with_me.domain.comment.util.CommentUtil;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentUtil commentUtil;

    @Transactional
    public Boolean createComment(CommentCreateRequestDto requestDto) {
        Comment comment = requestDto.createComment();
        commentRepository.save(comment);

        return true;
    }

    @Transactional
    public Boolean deleteComment(CommentDeleteRequestDto requestDto, Long memberId) {
        Comment comment = commentUtil.findComment(requestDto.getCommentId());

        if (comment.getMemberId().equals(memberId)) {
            commentRepository.delete(comment);

            return true;
        } else {
            return false;
        }
    }
}
