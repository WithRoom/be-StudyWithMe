package project.study_with_me.domain.study.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.study.dto.CreateStudyRequestDto;
import project.study_with_me.domain.study.dto.StudyResponseDto;
import project.study_with_me.domain.study.service.StudyService;

@Tag(name = "Study", description = "스터디 생성 및 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    @Operation(summary = "스터디 생성")
    @PostMapping("/create")
    public ResponseEntity<String> createStudy(@RequestBody CreateStudyRequestDto createStudyRequestDto) {
        return ResponseEntity.ok(studyService.createStudy(createStudyRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 조회")
    @GetMapping("/mypage/info")
    public ResponseEntity<StudyResponseDto> studyInfo() {
        return ResponseEntity.ok(studyService.studyInfo(SecurityUtil.getCurrentMemberId()));
    }
}
