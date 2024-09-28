package project.study_with_me.domain.study.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@Schema(name = "참여 신청 온 스터디 조회", description = "memberId, preferredArea, nickName 은 참여 신청 한 유저 정보")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResponseSignUpStudies {

    private List<ResponseSignUpStudy> responseSignUpStudies;

    public ResponseSignUpStudy createResponseSignUpStudy(Study study, Boolean interest, Member member) {
        return ResponseSignUpStudy.builder()
                .memberId(member.getMemberId())
                .preferredArea(member.getPreferredArea())
                .nickName(member.getNickName())
                .studyId(study.getStudyId())
                .studyImageUrl(study.getStudyImageUrl())
                .state(study.getSchedule().getState())
                .type(study.getType())
                .title(study.getTitle())
                .nowPeople(study.getNowPeople())
                .topic(study.getTopic())
                .finish(study.getFinish())
                .difficulty(study.getDifficulty())
                .recruitPeople(study.getRecruitPeople())
                .interest(interest)
                .build();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class ResponseSignUpStudy {

        private Long studyId;
        private Boolean state;  // 진행 중, 종료됨
        private String type;    // 온, 오프라인
        private String studyImageUrl;
        private String topic;   // 주제
        private String difficulty;  // 난이도
        private String title;
        private Integer recruitPeople;   // 참여인원
        private Integer nowPeople;  // 현재인원
        private Boolean finish; // 마감
        private Boolean interest;
        private Long memberId;
        private String nickName;
        private String preferredArea;
    }
}
