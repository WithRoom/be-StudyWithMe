package project.study_with_me.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import project.study_with_me.auth.client.KakaoClient;
import project.study_with_me.domain.member.repository.MemberRepository;

import static project.study_with_me.auth.text.KakaoTexts.GRANT_TYPE;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginService {

    private final MemberRepository memberRepository;
    private final KakaoClient kakaoClient;

    @Value("${kakao.client_id}") 
    private String clientId;

    public String getAccessTokenFromKakao(String code) {

        String result = kakaoClient.getToken(
                GRANT_TYPE.getText(),
                clientId,
                code
        );

        System.out.println(result);

        return null;
    }
}
