package project.study_with_me.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.domain.service.StudyService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudyController {

    @Value("${kakao.client_id}")
    private String client_id;

    private final StudyService studyService;

    @GetMapping("/kakaoLogin")
    public String test(@RequestBody String code) {
        log.info(code);
        return code;
    }
}
