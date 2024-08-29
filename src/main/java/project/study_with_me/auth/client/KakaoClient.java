package project.study_with_me.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao", url = "https://kauth.kakao.com")
public interface KakaoClient {

    @PostMapping("/oauth/token")
    String getToken(@RequestParam("grant_type") String grantType,
                    @RequestParam("client_id") String clientId,
                    @RequestParam("code") String code);
}
