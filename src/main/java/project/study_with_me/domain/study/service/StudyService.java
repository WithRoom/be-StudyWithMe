package project.study_with_me.domain.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.study.dto.request.CreateStudyRequestDto;
import project.study_with_me.domain.study.dto.request.StudyInterestRequestDto;
import project.study_with_me.domain.study.dto.request.StudyJoinRequestDto;
import project.study_with_me.domain.study.dto.request.ResponseJoinStudyRequestDto;
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

        StudyJoin studyJoin = studyJoinRequestDto.createStudyJoin(memberId, study.getGroupLeader());
        studyJoinRepository.save(studyJoin);

        return SUCCESS_JOIN_STUDY.getText();
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
        }

        studyJoinRepository.delete(studyJoin);

        return SUCCESS_JOIN_RESPONSE_STUDY.getText();
    }

    @Transactional(readOnly = true)
    public GroupLeaderStudies studyInfoMyStudy(Long memberId) {
        GroupLeaderStudies groupLeaderStudies = dtoUtils.createGroupLeaderStudies(memberId);

        return groupLeaderStudies;
    }

    @Transactional(readOnly = true)
    public ParticipationStudies studyInfoParticipationStudy(Long memberId) {
        ParticipationStudies participationStudies = dtoUtils.createParticipationStudies(memberId);

        return participationStudies;
    }

    @Transactional(readOnly = true)
    public SignUpStudies studyInfoSignUpStudy(Long memberId) {
        SignUpStudies signUpStudies = dtoUtils.createSignUpStudies(memberId);

        return signUpStudies;
    }

    @Transactional(readOnly = true)
    public ResponseSignUpStudies studyInfoRequestSignUpStudy(Long memberId) {
        ResponseSignUpStudies responseSignUpStudies = dtoUtils.createResponseSignUpStudies(memberId);

        return responseSignUpStudies;
    }

    @Transactional(readOnly = true)
    public InterestStudies studyInfoInterestStudy(Long memberId) {
        InterestStudies interestStudies = dtoUtils.createInterestStudies(memberId);

        return interestStudies;
    }
}
