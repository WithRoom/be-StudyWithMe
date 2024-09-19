package project.study_with_me.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.member.dto.request.CreateMemberInfoRequestDto;
import project.study_with_me.domain.member.dto.request.MemberInfoRequestDto;
import project.study_with_me.domain.member.dto.response.MemberInfoResponseDto;
import project.study_with_me.domain.member.service.MemberService;

@Tag(name = "유저", description = "유저 정보 조회 및 수정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "유저 정보 조회")
    @GetMapping("/mypage/info")
    public ResponseEntity<MemberInfoResponseDto> memberInfo() {
        return ResponseEntity.ok(memberService.memberInfo(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "유저 디테일 정보 생성")
    @PostMapping("/create/info")
    public ResponseEntity<Boolean> createMemberInfo(@RequestBody CreateMemberInfoRequestDto createMemberInfoRequestDto) {
        return ResponseEntity.ok(memberService.createMemberInfo(createMemberInfoRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "유저 정보 수정")
    @PostMapping("/mypage/update")
    public ResponseEntity<Boolean> memberUpdate(@RequestBody MemberInfoRequestDto memberInfoRequestDto) {
        return ResponseEntity.ok(memberService.memberUpdate(memberInfoRequestDto, SecurityUtil.getCurrentMemberId()));
    }
}
