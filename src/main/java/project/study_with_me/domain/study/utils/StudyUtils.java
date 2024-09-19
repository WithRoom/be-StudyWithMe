package project.study_with_me.domain.study.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.domain.study.entity.StudyJoin;
import project.study_with_me.domain.study.repository.StudyJoinRepository;
import project.study_with_me.domain.study.repository.StudyRepository;

import static project.study_with_me.text.StudyTexts.*;

@Component
@RequiredArgsConstructor
public class StudyUtils {

    private final StudyRepository studyRepository;
    private final StudyJoinRepository studyJoinRepository;

    public Study findStudy(Long studyId) {
        return studyRepository.findById(studyId)
                .orElseThrow(() -> new RuntimeException(NO_SEARCH_STUDY.getText()));
    }

    public StudyJoin findStudyJoin(Long studyId, Long memberId) {
        return studyJoinRepository.findByStudyIdAndJoinMemberId(studyId, memberId)
                .orElseThrow(() -> new RuntimeException(NO_SEARCH_JOIN_STUDY.getText()));
    }
}
