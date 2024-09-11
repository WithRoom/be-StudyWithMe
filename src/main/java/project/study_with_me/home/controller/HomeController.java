package project.study_with_me.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.home.dto.HomeInfoResponseDto;
import project.study_with_me.home.service.HomeService;

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
}
