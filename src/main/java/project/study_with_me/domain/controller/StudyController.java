package project.study_with_me.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.domain.dto.RequestDto;
import project.study_with_me.domain.service.StudyService;

@RestController
@RequiredArgsConstructor
public class StudyController {

    @Value("${kakao.client_id}")
    private String client_id;

    private final StudyService studyService;

    @PostMapping("/kakaoLogin")
    public String test(@RequestBody RequestDto requestDto) {
        return requestDto.getCode();
    }

    @GetMapping("/test")
    public String test1() {
        return "hi";
    }
}
