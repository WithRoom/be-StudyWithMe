package project.study_with_me.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.member.dto.CreateMemberInfoRequestDto;
import project.study_with_me.domain.member.dto.MemberInfoRequestDto;
import project.study_with_me.domain.member.dto.MemberInfoResponseDto;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.utils.MemberUtils;

import static project.study_with_me.text.MemberTexts.SUCCESS_MEMBER_INFO_CREATE;
import static project.study_with_me.text.MemberTexts.SUCCESS_UPDATE;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberUtils memberUtils;

    public MemberInfoResponseDto memberInfo(Long memberId) {
        Member member = memberUtils.findMember(memberId);

        return member.createMemberInfoResponseDto();
    }

    @Transactional
    public String createMemberInfo(CreateMemberInfoRequestDto createMemberInfoRequestDto, Long memberId) {
        Member member = memberUtils.findMember(memberId);

        member.createMemberInfo(createMemberInfoRequestDto);

        return SUCCESS_MEMBER_INFO_CREATE.getText();
    }

    @Transactional
    public String memberUpdate(MemberInfoRequestDto memberInfoRequestDto, Long memberId) {
        Member member = memberUtils.findMember(memberId);

        member.updateMemberInfo(memberInfoRequestDto);

        return SUCCESS_UPDATE.getText();
    }
}
