package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.entity.StudyMember;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinStudyRequestDto {

    private Long studyId;
    private Long memberId;
    private Boolean state;

    public StudyMember createStudyMember() {
        return StudyMember.builder()
                .studyId(studyId)
                .memberId(memberId)
                .build();
    }
}
