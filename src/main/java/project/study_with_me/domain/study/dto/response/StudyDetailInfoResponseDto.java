package project.study_with_me.domain.study.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.study.dto.StudyDetail;
import project.study_with_me.domain.study.dto.StudyGroupLeader;
import project.study_with_me.domain.study.dto.StudyScheduleDetail;
import project.study_with_me.domain.study.entity.Study;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyDetailInfoResponseDto {

    private StudyDetail studyDetail;
    private StudyGroupLeader studyGroupLeader;
    private StudyScheduleDetail studyScheduleDetail;

    public StudyDetailInfoResponseDto(Study study, Member member) {
        this.studyDetail = createStudyDetail(study);
        this.studyGroupLeader = createStudyGroupLeader(member);
        this.studyScheduleDetail = createStudyScheduleDetail(study);
    }

    public StudyDetail createStudyDetail(Study study) {
        return StudyDetail.builder()
                .studyId(study.getStudyId())
                .tag(study.getTag())
                .studyImageUrl(study.getStudyImageUrl())
                .topic(study.getTopic())
                .state(study.getSchedule().getState())
                .title(study.getTitle())
                .introduction(study.getIntroduction())
                .difficulty(study.getDifficulty())
                .build();
    }

    public StudyGroupLeader createStudyGroupLeader(Member member) {
        return StudyGroupLeader.builder()
                .preferredArea(member.getPreferredArea())
                .profileImage(member.getProfileImage())
                .name(member.getName())
                .build();
    }

    public StudyScheduleDetail createStudyScheduleDetail(Study study) {
        return StudyScheduleDetail.builder()
                .time(study.getSchedule().getTime())
                .recruitPeople(study.getRecruitPeople())
                .weekDay(study.getSchedule().getWeekDay())
                .startDay(study.getSchedule().getStartDay())
                .nowPeople(study.getNowPeople())
                .state(study.getSchedule().getState())
                .build();
    }
}
