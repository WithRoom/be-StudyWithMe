package project.study_with_me.home.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.domain.study.entity.StudyMember;
import project.study_with_me.domain.study.repository.StudyMemberRepository;
import project.study_with_me.domain.study.repository.StudyRepository;
import project.study_with_me.home.dto.HomeInfoResponseDto;
import project.study_with_me.home.dto.HomeStudyInfo;
import project.study_with_me.home.dto.MemberInfo;
import project.study_with_me.home.dto.StudyMemberInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;
    private final StudyMemberRepository studyMemberRepository;


    public HomeInfoResponseDto getHomeInfo(Long memberId) {

        HomeInfoResponseDto homeInfoResponseDto = new HomeInfoResponseDto();

        List<HomeStudyInfo> homeStudyInfoList = new ArrayList<>();
        // 스터디 조회
        List<Study> studies = studyRepository.findAllByOrderByStartDayDesc();
        for (Study study : studies) {
            homeStudyInfoList.add(homeInfoResponseDto.createHomeStudyInfo(study));
        }

        homeInfoResponseDto.setHomeStudyInfoList(homeStudyInfoList);

        // 사용자 조회
        /**
         *  memberId 가 null 인 경우 확인해야 함
         */
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 유저가 없습니다."));

        MemberInfo memberInfo = homeInfoResponseDto.createMemberInfo(member);

        homeInfoResponseDto.setMemberInfo(memberInfo);


        List<StudyMemberInfo> studyMemberInfoList = new ArrayList<>();
        // 참여중 스터디 조회
        List<StudyMember> findStudyMember = studyMemberRepository.findByMemberId(memberId);
        for (StudyMember studyMember : findStudyMember) {
            Long studyId = studyMember.getStudyId();
            Study study = studyRepository.findById(studyId)
                    .orElseThrow(() -> new RuntimeException("해당 스터디가 없습니다."));

            studyMemberInfoList.add(homeInfoResponseDto.createStudyMemberInfo(study));
        }

        homeInfoResponseDto.setStudyMemberInfoList(studyMemberInfoList);

        return homeInfoResponseDto;
    }
}
