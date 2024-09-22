package project.study_with_me.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.home.dto.response.HomeInfoResponseDto;
import project.study_with_me.home.dto.response.StudySearchFilterInfo;
import project.study_with_me.home.service.HomeService;

@Tag(name = "홈 화면", description = "홈 화면 및 검색 필터링")
@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @Operation(summary = "홈 화면 조회")
    @GetMapping("/info")
    public ResponseEntity<HomeInfoResponseDto> getHomeInfo() {
        return ResponseEntity.ok(homeService.getHomeInfo(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 조회 필터링")
    @GetMapping("/filter/info")
    public ResponseEntity<StudySearchFilterInfo> searchStudies(
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) String weekDay,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Boolean state
    ) {
        return ResponseEntity.ok(homeService.searchStudies(topic, difficulty, weekDay, type, state));
    }
}
