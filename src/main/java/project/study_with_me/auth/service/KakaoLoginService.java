package project.study_with_me.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.auth.client.KakaoTokenClient;
import project.study_with_me.auth.client.KakaoUserClient;
import project.study_with_me.auth.dto.KakaoLoginResponseDto;
import project.study_with_me.auth.dto.KakaoTokenResponseDto;
import project.study_with_me.auth.dto.KakaoUserResponseDto;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;

import static project.study_with_me.auth.text.KakaoTexts.BEARER;
import static project.study_with_me.auth.text.KakaoTexts.GRANT_TYPE;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoLoginService {

    private final MemberRepository memberRepository;
    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoUserClient kakaoUserClient;

    @Value("${kakao.client_id}") 
    private String clientId;

    /**
     * Kakao 에서 받은 code 를 사용해 Token 데이터 받기
     */
    public KakaoLoginResponseDto getAccessTokenFromKakao(String code) {
        KakaoTokenResponseDto kakaoTokenResponseDto = kakaoTokenClient.getToken(
                GRANT_TYPE.getText(),
                clientId,
                code
        );

        return KakaoLoginResponseDto.builder()
                .accessToken(kakaoTokenResponseDto.accessToken)
                .expireTime(kakaoTokenResponseDto.getExpiresIn())
                .build();
    }

    /**
     * Kakao User API 를 호출해 User Info 조회
     */
    @Transactional
    public Member getUserInfo(String accessToken) {
        String authorizationHeader = BEARER.getText() + accessToken;
        KakaoUserResponseDto userInfo = kakaoUserClient.getUserInfo(authorizationHeader);

        // Member 조회
        return memberRepository.findById(Long.valueOf(userInfo.getId()))
                .orElseGet(() -> {  /** 신규 회원인 경우*/
                    Member member = userInfo.createMember();
                    memberRepository.save(member);
                    System.out.println("신규");
                    return member;
                });
    }
}
