package project.study_with_me.domain.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.study.entity.Schedule;
import project.study_with_me.domain.study.entity.Study;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudyRequestDto {

    private StudyInfo studyInfo;
    private StudySchedule studySchedule;

    public Study createStudy(Long memberId) {
        return Study.builder()
                .studyImageUrl(studyInfo.getStudyImageUrl())
                .title(studyInfo.getTitle())
                .groupLeader(memberId)
                .tag(studyInfo.getTag())
                .topic(studyInfo.getTopic())
                .type(studyInfo.getType())
                .nowPeople(1)
                .introduction(studyInfo.getIntroduction())
                .recruitPeople(studyInfo.getRecruitPeople())
                .build();
    }

    public Schedule createSchedule(Long studyId) {
        return Schedule.builder()
                .time(studySchedule.getTime())
                .scheduleId(studyId)
                .period(studySchedule.getPeriod())
                .startDay(studySchedule.getStartDay())
                .weekDay(studySchedule.getWeekDay())
                .state(true)
                .build();
    }
}
