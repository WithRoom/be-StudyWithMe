package project.study_with_me.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.study_with_me.domain.member.dto.request.CreateMemberInfoRequestDto;
import project.study_with_me.domain.member.dto.request.MemberInfoRequestDto;
import project.study_with_me.domain.member.dto.response.MemberInfoResponseDto;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.utils.MemberUtils;

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
    public Boolean createMemberInfo(CreateMemberInfoRequestDto createMemberInfoRequestDto, Long memberId) {
        Member member = memberUtils.findMember(memberId);

        member.createMemberInfo(createMemberInfoRequestDto);

        return true;
    }

    @Transactional
    public Boolean memberUpdate(MemberInfoRequestDto memberInfoRequestDto, Long memberId) {
        Member member = memberUtils.findMember(memberId);

        member.updateMemberInfo(memberInfoRequestDto);

        return true;
    }
}
