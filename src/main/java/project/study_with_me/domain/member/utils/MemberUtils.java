package project.study_with_me.domain.member.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.study_with_me.domain.member.entity.Member;
import project.study_with_me.domain.member.repository.MemberRepository;

import static project.study_with_me.text.MemberTexts.NO_SEARCH_MEMBER;

@Component
@RequiredArgsConstructor
public class MemberUtils {

    private final MemberRepository memberRepository;

    public Member findMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException(NO_SEARCH_MEMBER.getText()));
        return member;
    }
}
