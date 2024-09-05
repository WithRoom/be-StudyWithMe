package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseJoinStudyRequestDto {

    private Long studyId;
    private Long memberId;
    private Boolean state;
}
