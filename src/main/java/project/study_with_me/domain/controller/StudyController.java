package project.study_with_me.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import project.study_with_me.domain.service.StudyService;

@Controller
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    public void test() {
        studyService.test();
    }

}
