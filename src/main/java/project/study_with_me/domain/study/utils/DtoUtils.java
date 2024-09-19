package project.study_with_me.domain.study.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.utils.MemberUtils;
import project.study_with_me.domain.study.dto.*;
import project.study_with_me.domain.study.dto.response.*;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.domain.study.entity.StudyInterest;
import project.study_with_me.domain.study.entity.StudyJoin;
import project.study_with_me.domain.study.entity.StudyMember;
import project.study_with_me.domain.study.repository.StudyInterestRepository;
import project.study_with_me.domain.study.repository.StudyJoinRepository;
import project.study_with_me.domain.study.repository.StudyMemberRepository;
import project.study_with_me.domain.study.repository.StudyRepository;

import java.util.ArrayList;
import java.util.List;

import static project.study_with_me.domain.study.dto.response.GroupLeaderStudies.*;
import static project.study_with_me.domain.study.dto.response.InterestStudies.*;
import static project.study_with_me.domain.study.dto.response.ParticipationStudies.*;
import static project.study_with_me.domain.study.dto.response.ResponseSignUpStudies.*;
import static project.study_with_me.domain.study.dto.response.SignUpStudies.*;

@Component
@RequiredArgsConstructor
public class DtoUtils {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyJoinRepository studyJoinRepository;
    private final StudyInterestRepository studyInterestRepository;
    private final StudyUtils studyUtils;
    private final MemberUtils memberUtils;

    public GroupLeaderStudies createGroupLeaderStudies(Long memberId) {
        List<Study> findGroupLeaders = studyRepository.findByGroupLeader(memberId);
        GroupLeaderStudies groupLeaderStudies = new GroupLeaderStudies(new ArrayList<>());

        for (Study findGroupLeaderStudy : findGroupLeaders) {
            Boolean interest = getInterest(memberId, findGroupLeaderStudy.getStudyId());

            GroupLeaderStudy groupLeaderStudy = new GroupLeaderStudies().createGroupLeaderStudy(findGroupLeaderStudy, interest);
            groupLeaderStudies.getGroupLeaderStudies().add(groupLeaderStudy);
        }

        return groupLeaderStudies;
    }

    public ParticipationStudies createParticipationStudies(Long memberId) {
        List<StudyMember> findStudyMember = studyMemberRepository.findByMemberId(memberId);
        ParticipationStudies participationStudies = new ParticipationStudies(new ArrayList<>());

        for (StudyMember studyMember : findStudyMember) {
            Study study = studyUtils.findStudy(studyMember.getStudyId());

            Boolean interest = getInterest(memberId, study.getStudyId());

            ParticipationStudy participationStudy = new ParticipationStudies().createParticipationStudy(study, interest);
            participationStudies.getParticipationStudies().add(participationStudy);
        }

        return participationStudies;
    }

    public SignUpStudies createSignUpStudies(Long memberId) {
        List<StudyJoin> findStudyJoin = studyJoinRepository.findByJoinMemberId(memberId);
        SignUpStudies signUpStudies = new SignUpStudies(new ArrayList<>());

        for (StudyJoin studyJoin : findStudyJoin) {
            Study study = studyUtils.findStudy(studyJoin.getStudyId());

            Boolean interest = getInterest(memberId, study.getStudyId());

            SignUpStudy signUpStudy = new SignUpStudies().createSignUpStudy(study, interest);
            signUpStudies.getSignUpStudies().add(signUpStudy);
        }

        return signUpStudies;
    }

    public ResponseSignUpStudies createResponseSignUpStudies(Long memberId) {
        List<StudyJoin> findStudyJoin = studyJoinRepository.findByGroupLeaderId(memberId);
        ResponseSignUpStudies responseSignUpStudies = new ResponseSignUpStudies(new ArrayList<>());

        for (StudyJoin studyJoin : findStudyJoin) {
            Study study = studyUtils.findStudy(studyJoin.getStudyId());
            Member member = memberUtils.findMember(studyJoin.getJoinMemberId());

            Boolean interest = getInterest(memberId, study.getStudyId());

            ResponseSignUpStudy responseSignUpStudy = new ResponseSignUpStudies().createResponseSignUpStudy(study, interest, member);
            responseSignUpStudies.getResponseSignUpStudies().add(responseSignUpStudy);
        }

        return responseSignUpStudies;
    }

    public InterestStudies createInterestStudies(Long memberId) {
        List<StudyInterest> findInterestStudy = studyInterestRepository.findByMemberId(memberId);
        InterestStudies interestStudies = new InterestStudies(new ArrayList<>());

        for (StudyInterest studyInterest : findInterestStudy) {
            Study study = studyUtils.findStudy(studyInterest.getStudyId());

            InterestStudy interestStudy = new InterestStudies().createInterestStudy(study, true);
            interestStudies.getInterestStudies().add(interestStudy);
        }

        return interestStudies;
    }

    public Boolean getInterest(Long memberId, Long studyId) {
        StudyInterest studyInterest = studyInterestRepository.findByMemberIdAndStudyId(memberId, studyId)
                .orElse(null);

        if (studyInterest != null) {
            return true;
        } else {
            return false;
        }
    }
}
