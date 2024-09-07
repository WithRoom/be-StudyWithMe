package project.study_with_me.domain.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.dto.ParticipationStudy;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ParticipationStudies {

    private List<ParticipationStudy> participationStudies;

    public ParticipationStudy createParticipationStudy(Study study) {
        return ParticipationStudy.builder()
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
