package project.study_with_me.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.domain.member.dto.request.CreateMemberInfoRequestDto;
import project.study_with_me.domain.member.dto.request.MemberInfoRequestDto;
import project.study_with_me.domain.member.dto.response.MemberInfoResponseDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "preferred_area")
    private String preferredArea;   // 선호지역

    @Column(name = "interest")
    private String interest;    // 관심분야

    @Column(name = "sub")
    private String sub; // Token Password 대체 사용

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    public void createMemberInfo(CreateMemberInfoRequestDto createMemberInfoRequestDto) {
        this.interest = createMemberInfoRequestDto.getInterest();
        this.preferredArea = createMemberInfoRequestDto.getPreferredArea();
        this.nickName = createMemberInfoRequestDto.getNickName();
    }

    public void updateMemberInfo(MemberInfoRequestDto memberInfoRequestDto) {
        this.nickName = memberInfoRequestDto.getNickName();
        this.name = memberInfoRequestDto.getName();
        this.interest = memberInfoRequestDto.getInterest();
        this.preferredArea = memberInfoRequestDto.getPreferredArea();
        this.profileImage = memberInfoRequestDto.getProfileImage();
    }

    public MemberInfoResponseDto createMemberInfoResponseDto() {
        return MemberInfoResponseDto.builder()
                .name(name)
                .profileImage(profileImage)
                .nickName(nickName)
                .preferredArea(preferredArea)
                .interest(interest)
                .build();
    }
}
