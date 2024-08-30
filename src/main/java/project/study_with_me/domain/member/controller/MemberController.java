package project.study_with_me.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/info")
    public String test() {

        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new RuntimeException("gd"));

        return member.getEmail();
    }
}
