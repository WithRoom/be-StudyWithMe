package project.study_with_me.domain.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;
import project.study_with_me.domain.study.dto.request.*;
import project.study_with_me.domain.study.dto.response.*;
import project.study_with_me.domain.study.entity.*;
import project.study_with_me.domain.study.repository.StudyInterestRepository;
import project.study_with_me.domain.study.repository.StudyJoinRepository;
import project.study_with_me.domain.study.repository.StudyMemberRepository;
import project.study_with_me.domain.study.repository.StudyRepository;
import project.study_with_me.domain.study.utils.DtoUtils;

import static project.study_with_me.text.StudyTexts.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyInterestRepository studyInterestRepository;
    private final StudyJoinRepository studyJoinRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final MemberRepository memberRepository;
    private final DtoUtils dtoUtils;

    @Transactional
    public String createStudy(CreateStudyRequestDto createStudyRequestDto, Long memberId) {

        Study study = createStudyRequestDto.createStudy(memberId);
        studyRepository.save(study);

        Schedule schedule = createStudyRequestDto.createSchedule(study.getStudyId());

        study.setSchedule(schedule);    // 한쪽만 설정해도 됨

        return SUCCESS_CREATE_STUDY.getText();
    }

    @Transactional
    public String interestStudy(StudyInterestRequestDto studyInterestRequestDto, Long memberId) {

        StudyInterest studyInterest = studyInterestRequestDto.createStudyInterest(memberId);
        studyInterestRepository.save(studyInterest);

        return SUCCESS_INTEREST_STUDY.getText();
    }

    @Transactional
    public String joinStudy(StudyJoinRequestDto studyJoinRequestDto, Long memberId) {

        Study study = studyRepository.findById(studyJoinRequestDto.getStudyId())
                .orElseThrow(() -> new RuntimeException("해당 스터디가 없습니다."));

        if (study.getGroupLeader() == memberId) {
            return GROUP_LEADER.getText();
        } else {
            StudyJoin studyJoin = studyJoinRequestDto.createStudyJoin(memberId, study.getGroupLeader());
            studyJoinRepository.save(studyJoin);

            return SUCCESS_JOIN_STUDY.getText();
        }
    }

    @Transactional
    public String responseJoinStudy(ResponseJoinStudyRequestDto responseJoinStudyRequestDto) {

        StudyJoin studyJoin = studyJoinRepository.findByStudyIdAndJoinMemberId(responseJoinStudyRequestDto.getStudyId(),
                        responseJoinStudyRequestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 스터디에 참여 요청이 없습니다."));

        if (responseJoinStudyRequestDto.getState().equals(true)) {
            StudyMember studyMember = StudyMember.builder()
                    .studyId(studyJoin.getStudyId())
                    .memberId(studyJoin.getJoinMemberId())
                    .build();

            studyMemberRepository.save(studyMember);

            /** NOTE
             * 좀 더 다른 로직 생각해봐야 함
             */
            Study study = studyRepository.findById(responseJoinStudyRequestDto.getStudyId())
                    .orElseThrow(() -> new RuntimeException("해당 스터디가 없습니다."));

            study.updateStudyNowPeople();
        }

        studyJoinRepository.delete(studyJoin);

        return SUCCESS_JOIN_RESPONSE_STUDY.getText();
    }

    @Transactional(readOnly = true)
    public GroupLeaderStudies studyInfoMyStudy(Long memberId) {
        return dtoUtils.createGroupLeaderStudies(memberId);
    }

    @Transactional(readOnly = true)
    public ParticipationStudies studyInfoParticipationStudy(Long memberId) {
        return dtoUtils.createParticipationStudies(memberId);
    }

    @Transactional(readOnly = true)
    public SignUpStudies studyInfoSignUpStudy(Long memberId) {
        return dtoUtils.createSignUpStudies(memberId);
    }

    @Transactional(readOnly = true)
    public ResponseSignUpStudies studyInfoRequestSignUpStudy(Long memberId) {
        return dtoUtils.createResponseSignUpStudies(memberId);
    }

    @Transactional(readOnly = true)
    public InterestStudies studyInfoInterestStudy(Long memberId) {
        return dtoUtils.createInterestStudies(memberId);
    }

    @Transactional(readOnly = true)
    public StudyDetailInfoResponseDto studyDetailInfo(StudyDetailRequestDto studyDetailRequestDto) {
        Study study = studyRepository.findById(studyDetailRequestDto.getStudyId())
                .orElseThrow(() -> new RuntimeException("해당 스터디가 없습니다."));

        Member member = memberRepository.findById(study.getGroupLeader())
                .orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));

        return new StudyDetailInfoResponseDto(study, member);
    }
}
