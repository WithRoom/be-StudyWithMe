package project.study_with_me.home.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.utils.MemberUtils;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.domain.study.entity.StudyMember;
import project.study_with_me.domain.study.repository.StudyMemberRepository;
import project.study_with_me.domain.study.repository.StudyRepository;
import project.study_with_me.domain.study.utils.DtoUtils;
import project.study_with_me.domain.study.utils.StudyUtils;
import project.study_with_me.home.dto.HomeStudyInfo;
import project.study_with_me.home.dto.MemberInfo;
import project.study_with_me.home.dto.StudyMemberInfo;
import project.study_with_me.home.dto.response.HomeInfoResponseDto;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HomeUtils {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final DtoUtils dtoUtils;
    private final MemberUtils memberUtils;
    private final StudyUtils studyUtils;

    public HomeInfoResponseDto searchStudy(HomeInfoResponseDto homeInfoResponseDto, Long memberId) {
        List<HomeStudyInfo> homeStudyInfoList = new ArrayList<>();
        List<Study> studies = studyRepository.findAllByOrderByStartDayDesc();

        for (Study study : studies) {
            Boolean interest = dtoUtils.getInterest(memberId, study.getStudyId());
            homeStudyInfoList.add(homeInfoResponseDto.createHomeStudyInfo(study, interest));
        }

        homeInfoResponseDto.setHomeStudyInfoList(homeStudyInfoList);

        return homeInfoResponseDto;
    }

    public HomeInfoResponseDto searchMember(HomeInfoResponseDto homeInfoResponseDto, Long memberId) {
        Member member;

        if (memberId == null) { // 로그인 하지 않은 경우
            member = new Member();
        } else {    // 로그인 한 경우
            member = memberUtils.findMember(memberId);
        }

        MemberInfo memberInfo = homeInfoResponseDto.createMemberInfo(member);
        homeInfoResponseDto.setMemberInfo(memberInfo);

        return homeInfoResponseDto;
    }

    public HomeInfoResponseDto searchJoinStudy(HomeInfoResponseDto homeInfoResponseDto, Long memberId) {
        List<StudyMemberInfo> studyMemberInfoList = new ArrayList<>();
        List<StudyMember> findStudyMember = studyMemberRepository.findByMemberId(memberId);

        for (StudyMember studyMember : findStudyMember) {
            Study study = studyUtils.findStudy(studyMember.getStudyId());

            studyMemberInfoList.add(homeInfoResponseDto.createStudyMemberInfo(study));
        }

        homeInfoResponseDto.setStudyMemberInfoList(studyMemberInfoList);

        return homeInfoResponseDto;
    }
}
