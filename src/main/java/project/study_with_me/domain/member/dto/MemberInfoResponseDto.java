package project.study_with_me.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoResponseDto {

    private String nickName;
    private String name;
    private String profileImage;
    private String preferredArea;   // 선호지역
    private String interest;    // 관심분야
}
