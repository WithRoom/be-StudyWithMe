package project.study_with_me.domain.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupLeaderStudies {

    private List<GroupLeaderStudy> groupLeaderStudies;

    public GroupLeaderStudy createGroupLeaderStudy(Study study, Boolean interest) {
        return GroupLeaderStudy.builder()
                .studyId(study.getStudyId())
                .studyImageUrl(study.getStudyImageUrl())
                .state(study.getSchedule().getState())
                .type(study.getType())
                .title(study.getTitle())
                .topic(study.getTopic())
                .nowPeople(study.getNowPeople())
                .difficulty(study.getDifficulty())
                .recruitPeople(study.getRecruitPeople())
                .interest(interest)
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class GroupLeaderStudy {

        private Long studyId;
        private Boolean state;  // 진행 중, 종료됨
        private String type;    // 온, 오프라인
        private String studyImageUrl;
        private String topic;   // 주제
        private String difficulty;  // 난이도
        private String title;
        private Integer recruitPeople;   // 참여인원
        private Integer nowPeople;  // 현재인원
        private Boolean interest;
    }
}
