package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyComment {

    private Long memberId;
    private Long commentId;
    private String nickName;
    private String profileImage;
    private String content;
}
