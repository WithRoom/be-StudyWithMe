package project.study_with_me.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.auth.dto.KakaoLoginRequestDto;
import project.study_with_me.auth.dto.KakaoLoginResponseDto;
import project.study_with_me.auth.service.KakaoLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @RequestMapping("/kakao")
    public ResponseEntity<KakaoLoginResponseDto> loginKakao(@RequestBody KakaoLoginRequestDto kakaoLoginRequestDto) {
        String accessToken = kakaoLoginService.getAccessTokenFromKakao(kakaoLoginRequestDto.getCode());
        return ResponseEntity.ok(new KakaoLoginResponseDto());
    }
}
