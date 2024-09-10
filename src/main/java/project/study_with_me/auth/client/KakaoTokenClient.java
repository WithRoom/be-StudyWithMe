package project.study_with_me.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.study_with_me.auth.dto.response.KakaoTokenResponseDto;

@FeignClient(name = "kakao-token-api", url = "https://kauth.kakao.com")
public interface KakaoTokenClient {

    @PostMapping("/oauth/token")
    KakaoTokenResponseDto getToken(@RequestParam("grant_type") String grantType,
                                   @RequestParam("client_id") String clientId,
                                   @RequestParam("code") String code);
}
