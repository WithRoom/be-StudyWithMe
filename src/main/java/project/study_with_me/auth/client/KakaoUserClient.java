package project.study_with_me.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import project.study_with_me.auth.dto.KakaoUserResponseDto;

@FeignClient(name = "kakao-user-api", url = "https://kapi.kakao.com")
public interface KakaoUserClient {

    @GetMapping("/v2/user/me")
    KakaoUserResponseDto getUserInfo(@RequestHeader("Authorization") String authorizationHeader);
}
