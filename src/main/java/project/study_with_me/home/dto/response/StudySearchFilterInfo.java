package project.study_with_me.home.dto.response;

import lombok.*;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.home.dto.HomeStudyInfo;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class StudySearchFilterInfo {

    private List<HomeStudyInfo> homeStudyInfoList;

    public HomeStudyInfo createHomeStudyInfo(Study study, Boolean interest) {
        return HomeStudyInfo.builder()
                .recruitPeople(study.getRecruitPeople())
                .type(study.getType())
                .title(study.getTitle())
                .topic(study.getTopic())
                .studyId(study.getStudyId())
                .studyImageUrl(study.getStudyImageUrl())
                .interest(interest)
                .difficulty(study.getDifficulty())
                .state(study.getSchedule().getState())
                .nowPeople(study.getNowPeople())
                .build();
    }
}
