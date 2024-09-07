package project.study_with_me.domain.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.dto.InterestStudy;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InterestStudies {

    private List<InterestStudy> interestStudies;

    public InterestStudy createInterestStudy(Study study) {
        return InterestStudy.builder()
                .studyId(study.getStudyId())
                .studyImageUrl(study.getStudyImageUrl())
                .state(study.getSchedule().getState())
                .type(study.getType())
                .title(study.getTitle())
                .topic(study.getTopic())
                .difficulty(study.getDifficulty())
                .recruitPeople(study.getRecruitPeople())
                .build();
    }
}
