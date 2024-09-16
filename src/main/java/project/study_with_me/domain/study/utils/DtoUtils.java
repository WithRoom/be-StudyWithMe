package project.study_with_me.domain.study.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
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

import static project.study_with_me.text.StudyTexts.NO_SEARCH_STUDY;

@Component
@RequiredArgsConstructor
public class DtoUtils {

    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyJoinRepository studyJoinRepository;
    private final StudyInterestRepository studyInterestRepository;

    public GroupLeaderStudies createGroupLeaderStudies(Long memberId) {

        List<Study> findGroupLeaders = studyRepository.findByGroupLeader(memberId);
        GroupLeaderStudies groupLeaderStudies = new GroupLeaderStudies(new ArrayList<>());

        for (Study findGroupLeaderStudy : findGroupLeaders) {
            StudyInterest studyInterest = studyInterestRepository.findByMemberIdAndStudyId(memberId, findGroupLeaderStudy.getStudyId())
                    .orElse(null);

            Boolean interest = false;

            if (studyInterest != null) {
                interest = true;
            }
            GroupLeaderStudy groupLeaderStudy = new GroupLeaderStudies().createGroupLeaderStudy(findGroupLeaderStudy, interest);
            groupLeaderStudies.getGroupLeaderStudies().add(groupLeaderStudy);
        }

        return groupLeaderStudies;
    }

    public ParticipationStudies createParticipationStudies(Long memberId) {

        List<StudyMember> findStudyMember = studyMemberRepository.findByMemberId(memberId);
        ParticipationStudies participationStudies = new ParticipationStudies(new ArrayList<>());

        for (StudyMember studyMember : findStudyMember) {
            Study study = studyRepository.findById(studyMember.getStudyId())
                    .orElseThrow(() -> new RuntimeException(NO_SEARCH_STUDY.getText()));

            StudyInterest studyInterest = studyInterestRepository.findByMemberIdAndStudyId(memberId, study.getStudyId())
                    .orElse(null);

            Boolean interest = false;

            if (studyInterest != null) {
                interest = true;
            }

            ParticipationStudy participationStudy = new ParticipationStudies().createParticipationStudy(study, interest);
            participationStudies.getParticipationStudies().add(participationStudy);
        }

        return participationStudies;
    }


    public SignUpStudies createSignUpStudies(Long memberId) {

        List<StudyJoin> findStudyJoin = studyJoinRepository.findByJoinMemberId(memberId);
        SignUpStudies signUpStudies = new SignUpStudies(new ArrayList<>());

        for (StudyJoin studyJoin : findStudyJoin) {
            Study study = studyRepository.findById(studyJoin.getStudyId())
                    .orElseThrow(() -> new RuntimeException(NO_SEARCH_STUDY.getText()));

            StudyInterest studyInterest = studyInterestRepository.findByMemberIdAndStudyId(memberId, study.getStudyId())
                    .orElse(null);

            Boolean interest = false;

            if (studyInterest != null) {
                interest = true;
            }

            SignUpStudy signUpStudy = new SignUpStudies().createSignUpStudy(study, interest);
            signUpStudies.getSignUpStudies().add(signUpStudy);
        }

        return signUpStudies;
    }

    public ResponseSignUpStudies createResponseSignUpStudies(Long memberId) {

        List<StudyJoin> findStudyJoin = studyJoinRepository.findByGroupLeaderId(memberId);
        ResponseSignUpStudies responseSignUpStudies = new ResponseSignUpStudies(new ArrayList<>());

        for (StudyJoin studyJoin : findStudyJoin) {
            Study study = studyRepository.findById(studyJoin.getStudyId())
                    .orElseThrow(() -> new RuntimeException(NO_SEARCH_STUDY.getText()));

            StudyInterest studyInterest = studyInterestRepository.findByMemberIdAndStudyId(memberId, study.getStudyId())
                    .orElse(null);

            Boolean interest = false;

            if (studyInterest != null) {
                interest = true;
            }

            ResponseSignUpStudy responseSignUpStudy = new ResponseSignUpStudies().createResponseSignUpStudy(study, interest, studyJoin.getJoinMemberId());
            responseSignUpStudies.getResponseSignUpStudies().add(responseSignUpStudy);
        }

        return responseSignUpStudies;
    }


    public InterestStudies createInterestStudies(Long memberId) {

        List<StudyInterest> findInterestStudy = studyInterestRepository.findByMemberId(memberId);
        InterestStudies interestStudies = new InterestStudies(new ArrayList<>());

        for (StudyInterest studyInterest : findInterestStudy) {
            Study study = studyRepository.findById(studyInterest.getStudyId())
                    .orElseThrow(() -> new RuntimeException("해당 스터디가 없습니다."));

            InterestStudy interestStudy = new InterestStudies().createInterestStudy(study, true);
            interestStudies.getInterestStudies().add(interestStudy);
        }

        return interestStudies;
    }
}
