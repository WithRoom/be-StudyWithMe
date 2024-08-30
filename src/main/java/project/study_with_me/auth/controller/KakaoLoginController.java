package project.study_with_me.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.auth.dto.KakaoLoginRequestDto;
import project.study_with_me.auth.dto.KakaoLoginResponseDto;
import project.study_with_me.auth.service.KakaoLoginService;

@Tag(name = "Auth", description = "Login")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;

    @Operation(summary = "Kakao Login(회원 가입 및 로그인)")
    @PostMapping("/kakao")
    public ResponseEntity<KakaoLoginResponseDto> loginKakao(@RequestBody KakaoLoginRequestDto kakaoLoginRequestDto) {
        KakaoLoginResponseDto responseDto = kakaoLoginService.getAccessTokenFromKakao(kakaoLoginRequestDto.getCode());

        // 위에서 받은 accessToken 으로 Kakao API 로 회원정보 조회 (로그인 및 회원가입)
        kakaoLoginService.getUserInfo(responseDto.getAccessToken());

        return ResponseEntity.ok(responseDto);
    }
}
