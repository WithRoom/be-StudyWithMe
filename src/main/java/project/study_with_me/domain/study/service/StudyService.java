package project.study_with_me.domain.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.comment.entity.Comment;
import project.study_with_me.domain.comment.repository.CommentRepository;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.utils.MemberUtils;
import project.study_with_me.domain.study.dto.StudyComment;
import project.study_with_me.domain.study.dto.request.*;
import project.study_with_me.domain.study.dto.response.*;
import project.study_with_me.domain.study.entity.*;
import project.study_with_me.domain.study.repository.StudyInterestRepository;
import project.study_with_me.domain.study.repository.StudyJoinRepository;
import project.study_with_me.domain.study.repository.StudyMemberRepository;
import project.study_with_me.domain.study.repository.StudyRepository;
import project.study_with_me.domain.study.utils.DtoUtils;
import project.study_with_me.domain.study.utils.StudyUtils;
import project.study_with_me.mail.service.MailService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyInterestRepository studyInterestRepository;
    private final StudyJoinRepository studyJoinRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final DtoUtils dtoUtils;
    private final CommentRepository commentRepository;
    private final StudyUtils studyUtils;
    private final MemberUtils memberUtils;
    private final MailService mailService;

    @Transactional
    public Boolean createStudy(CreateStudyRequestDto createStudyRequestDto, Long memberId) {
        Study study = createStudyRequestDto.createStudy(memberId);
        studyRepository.save(study);

        Schedule schedule = createStudyRequestDto.createSchedule(study.getStudyId());
        study.setSchedule(schedule);    // 한쪽만 설정해도 됨

        return true;
    }

    @Transactional
    public Boolean interestStudy(StudyInterestRequestDto studyInterestRequestDto, Long memberId) {
        StudyInterest studyInterest = studyInterestRepository
                .findByMemberIdAndStudyId(memberId, studyInterestRequestDto.getStudyId())
                .orElse(null);

        if (studyInterest == null) {    // 관심 스터디가 없으면 관심 등록
            StudyInterest interest = studyInterestRequestDto.createStudyInterest(memberId);
            studyInterestRepository.save(interest);
        } else {    // 관심 스터디가 있으면 관심 해제
            studyInterestRepository.delete(studyInterest);
        }

        return true;
    }

    @Transactional
    public Boolean joinStudy(StudyJoinRequestDto studyJoinRequestDto, Long memberId) {
        Study study = studyUtils.findStudy(studyJoinRequestDto.getStudyId());

        if (study.getGroupLeader().equals(memberId)) {  // 그룹장인 경우
            return false;
        }

        StudyJoin findStudyJoin = studyJoinRepository.findByStudyIdAndJoinMemberId(study.getStudyId(), memberId)
                .orElse(null);
        StudyMember studyMember = studyMemberRepository.findByMemberIdAndStudyId(memberId, study.getStudyId())
                .orElse(null);

        if (findStudyJoin != null || studyMember != null) { // 이미 신청한 스터디, 참여 중인 스터디인 경우
            return false;
        } else {
            StudyJoin studyJoin = studyJoinRequestDto.createStudyJoin(memberId, study.getGroupLeader());
            studyJoinRepository.save(studyJoin);

            Member member = memberUtils.findMember(studyJoin.getGroupLeaderId());
            mailService.sendRequestEmailNotice(member.getEmail(), study.getTitle(), member.getName());   // 메일 전송

            return true;
        }
    }

    @Transactional
    public Boolean responseJoinStudy(JoinStudyRequestDto joinStudyRequestDto) {
        StudyJoin studyJoin = studyUtils.findStudyJoin(joinStudyRequestDto.getStudyId(), joinStudyRequestDto.getMemberId());
        Member member = memberUtils.findMember(joinStudyRequestDto.getMemberId());
        Study study = studyUtils.findStudy(joinStudyRequestDto.getStudyId());

        Boolean check = false;
        if (joinStudyRequestDto.getState().equals(true)) {  // 참여 수락
            StudyMember studyMember = joinStudyRequestDto.createStudyMember();
            studyMemberRepository.save(studyMember);

            study.updateStudyNowPeople();

            check = true;
        }

        studyJoinRepository.delete(studyJoin);

        mailService.sendAcceptEmailNotice(member.getEmail(), check, study.getTitle(), member.getName(), study.getKakaoOpenChanUrl());    // 메일 전송

        return true;
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
        Study study = studyUtils.findStudy(studyDetailRequestDto.getStudyId());
        Member member = memberUtils.findMember(study.getGroupLeader());

        StudyDetailInfoResponseDto studyDetailInfoResponseDto = new StudyDetailInfoResponseDto(study, member);

        List<StudyComment> studyCommentList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findByStudyId(study.getStudyId());

        for (Comment comment : commentList) {
            Member findMember = memberUtils.findMember(comment.getMemberId());

            StudyComment studyComment = studyDetailInfoResponseDto.createStudyComment(comment, findMember);
            studyCommentList.add(studyComment);
        }

        studyDetailInfoResponseDto.setStudyCommentList(studyCommentList);

        return studyDetailInfoResponseDto;
    }

    @Transactional
    public Boolean studyFinish(StudyFinishRequestDto studyFinishRequestDto, Long memberId) {
        Study study = studyUtils.findStudy(studyFinishRequestDto.getStudyId());
        if (study.getGroupLeader().equals(memberId)) {  // 그룹장 체크
            study.finishStudy();
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean studyDelete(StudyDeleteRequestDto studyDeleteRequestDto, Long memberId) {
        Study study = studyUtils.findStudy(studyDeleteRequestDto.getStudyId());

        if (study.getGroupLeader().equals(memberId)) {
            studyRepository.delete(study);
            return true;
        } else {
            return false;
        }
    }
}
