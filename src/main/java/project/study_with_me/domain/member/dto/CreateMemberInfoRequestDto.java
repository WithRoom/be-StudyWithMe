package project.study_with_me.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberInfoRequestDto {

    private String nickName;
    private String preferredArea;   // 선호지역
    private String interest;    // 관심분야
}
