package project.study_with_me.domain.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.study.dto.CreateStudyRequestDto;
import project.study_with_me.domain.study.dto.StudyResponseDto;
import project.study_with_me.domain.study.entity.Schedule;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.domain.study.repository.StudyRepository;

import static project.study_with_me.domain.study.text.StudyTexts.SUCCESS_CREATE_STUDY;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;

    @Transactional
    public String createStudy(CreateStudyRequestDto createStudyRequestDto, Long memberId) {

        Study study = createStudyRequestDto.createStudy(memberId);
        studyRepository.save(study);

        Schedule schedule = createStudyRequestDto.createSchedule(study.getStudyId());

        study.setSchedule(schedule);    // 한쪽만 설정해도 됨

        return SUCCESS_CREATE_STUDY.getText();
    }

    public StudyResponseDto studyInfo() {




        return null;
    }
}
