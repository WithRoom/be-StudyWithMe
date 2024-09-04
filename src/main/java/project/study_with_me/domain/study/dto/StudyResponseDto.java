package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyResponseDto {

    private GroupLeaderStudy groupLeaderStudy;
    private ParticipationStudy participationStudy;
    private SignUpStudy signUpStudy;
    private InterestStudy interestStudy;
}
