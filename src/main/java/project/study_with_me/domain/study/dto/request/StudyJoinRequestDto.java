package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.entity.StudyJoin;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyJoinRequestDto {

    private Long studyId;

    public StudyJoin createStudyJoin(Long memberId, Long groupLeaderId) {
        return StudyJoin.builder()
                .state(false)
                .joinMemberId(memberId)
                .studyId(studyId)
                .groupLeaderId(groupLeaderId)
                .build();
    }
}
