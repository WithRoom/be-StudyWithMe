package project.study_with_me.domain.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.dto.ResponseSignUpStudy;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseSignUpStudies {

    private List<ResponseSignUpStudy> responseSignUpStudies;

    public ResponseSignUpStudy createResponseSignUpStudy(Study study, Boolean interest, Long memberId) {
        return ResponseSignUpStudy.builder()
                .memberId(memberId)
                .studyId(study.getStudyId())
                .studyImageUrl(study.getStudyImageUrl())
                .state(study.getSchedule().getState())
                .type(study.getType())
                .title(study.getTitle())
                .nowPeople(study.getNowPeople())
                .topic(study.getTopic())
                .difficulty(study.getDifficulty())
                .recruitPeople(study.getRecruitPeople())
                .interest(interest)
                .build();
    }
}
