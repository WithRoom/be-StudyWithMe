package project.study_with_me.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.study_with_me.domain.member.entity.Authority;
import project.study_with_me.domain.member.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("connected_at")
    private String connectedAt;

    @JsonProperty("properties")
    private Properties properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("is_default_nickname")
        private boolean isDefaultNickname;
    }

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoAccount {

        @JsonProperty("profile_nickname_needs_agreement")
        private boolean profileNicknameNeedsAgreement;

        @JsonProperty("profile")
        private Profile profile;

        @JsonProperty("has_email")
        private boolean hasEmail;

        @JsonProperty("email_needs_agreement")
        private boolean emailNeedsAgreement;

        @JsonProperty("is_email_valid")
        private boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private boolean isEmailVerified;

        @JsonProperty("email")
        private String email;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Profile {

            @JsonProperty("nickname")
            private String nickname;

            @JsonProperty("is_default_nickname")
            private boolean isDefaultNickname;
        }
    }

    public Member createMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(Long.valueOf(id))
                .nickName(properties.getNickname())
                .email(kakaoAccount.getEmail())
                .sub(passwordEncoder.encode(id))
                .authority(Authority.ROLE_USER)
                .build();
    }
}
