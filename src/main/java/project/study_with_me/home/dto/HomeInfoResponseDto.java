package project.study_with_me.home.dto;

import lombok.*;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class HomeInfoResponseDto {

    private List<HomeStudyInfo> homeStudyInfoList;
    private MemberInfo memberInfo;
    private List<StudyMemberInfo> studyMemberInfoList;

    public HomeStudyInfo createHomeStudyInfo(Study study, Boolean interest) {
        return HomeStudyInfo.builder()
                .studyId(study.getStudyId())
                .state(study.getSchedule().getState())
                .topic(study.getTopic())
                .title(study.getTitle())
                .type(study.getType())
                .studyImageUrl(study.getStudyImageUrl())
                .difficulty(study.getDifficulty())
                .nowPeople(study.getNowPeople())
                .interest(interest)
                .build();
    }

    public MemberInfo createMemberInfo(Member member) {
        return MemberInfo.builder()
                .profileImage(member.getProfileImage())
                .nickName(member.getNickName())
                .build();
    }

    public StudyMemberInfo createStudyMemberInfo(Study study) {
        return StudyMemberInfo.builder()
                .state(study.getSchedule().getState())
                .startDay(study.getSchedule().getStartDay())
                .studyImageUrl(study.getStudyImageUrl())
                .studyId(study.getStudyId())
                .title(study.getTitle())
                .build();
    }

}
