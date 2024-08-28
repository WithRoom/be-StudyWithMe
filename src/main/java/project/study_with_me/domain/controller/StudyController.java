package project.study_with_me.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.domain.service.StudyService;

@Controller
@RequiredArgsConstructor
public class StudyController {

    @Value("${kakao.client_id}")
    private String client_id;

    private final StudyService studyService;

    @PostMapping("/kakaoLogin")
    public String test(@RequestBody String code) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@" + code);
        return code;
    }

    @GetMapping("/test")
    public String test1() {
        return "hi";
    }
}
