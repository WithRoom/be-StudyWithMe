package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.entity.StudyInterest;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyInterestRequestDto {

    private Long studyId;

    public StudyInterest createStudyInterest(Long memberId) {
        return StudyInterest.builder()
                .memberId(memberId)
                .studyId(studyId)
                .build();
    }
}
