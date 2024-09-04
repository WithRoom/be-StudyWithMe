package project.study_with_me.domain.member.dto;

import lombok.Getter;

@Getter
public class CreateMemberInfoRequestDto {

    private String nickName;
    private String preferredArea;   // 선호지역
    private String interest;    // 관심분야
}
