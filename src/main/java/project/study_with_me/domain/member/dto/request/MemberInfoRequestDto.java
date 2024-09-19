package project.study_with_me.domain.member.dto.request;

import lombok.Getter;

@Getter
public class MemberInfoRequestDto {

    private String profileImage;
    private String name;
    private String nickName;
    private String preferredArea;   // 선호지역
    private String interest;    // 관심분야
}
