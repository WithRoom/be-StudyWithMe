package project.study_with_me.domain.study.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.study.dto.request.CreateStudyRequestDto;
import project.study_with_me.domain.study.dto.request.StudyInterestRequestDto;
import project.study_with_me.domain.study.dto.request.StudyJoinRequestDto;
import project.study_with_me.domain.study.dto.request.ResponseJoinStudyRequestDto;
import project.study_with_me.domain.study.dto.response.*;
import project.study_with_me.domain.study.service.StudyService;

@Tag(name = "Study", description = "스터디 생성 및 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;

    @Operation(summary = "스터디 생성")
    @PostMapping("/create")
    public ResponseEntity<String> createStudy(@RequestBody CreateStudyRequestDto createStudyRequestDto) {
        return ResponseEntity.ok(studyService.createStudy(createStudyRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "관심 스터디 등록")
    @PostMapping("/interest")
    public ResponseEntity<String> interestStudy(@RequestBody StudyInterestRequestDto studyInterestRequestDto) {
        return ResponseEntity.ok(studyService.interestStudy(studyInterestRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 참여 신청")
    @PostMapping("/join")
    public ResponseEntity<String> joinStudy(@RequestBody StudyJoinRequestDto studyJoinRequestDto) {
        return ResponseEntity.ok(studyService.joinStudy(studyJoinRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 참여 수락 및 거절")
    @PostMapping("/response-join")
    public ResponseEntity<String> responseJoinStudy(@RequestBody ResponseJoinStudyRequestDto responseJoinStudyRequestDto) {
        return ResponseEntity.ok(studyService.responseJoinStudy(responseJoinStudyRequestDto));
    }

    @Operation(summary = "내가 만든 스터디 조회")
    @GetMapping("/mypage/info/mystudy")
    public ResponseEntity<GroupLeaderStudies> studyInfoMyStudy() {
        return ResponseEntity.ok(studyService.studyInfoMyStudy(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "참여 중 스터디 조회")
    @GetMapping("/mypage/info/part")
    public ResponseEntity<ParticipationStudies> studyInfoParticipationStudy() {
        return ResponseEntity.ok(studyService.studyInfoParticipationStudy(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "참여 신청 한 스터디 조회")
    @GetMapping("/mypage/info/join")
    public ResponseEntity<SignUpStudies> studyInfoSignUpStudy() {
        return ResponseEntity.ok(studyService.studyInfoSignUpStudy(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "참여 신청 온 스터디 조회")
    @GetMapping("/mypage/info/request-join")
    public ResponseEntity<ResponseSignUpStudies> studyInfoRequestSignUpStudy() {
        return ResponseEntity.ok(studyService.studyInfoRequestSignUpStudy(SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "관심 스터디 조회")
    @GetMapping("/mypage/info/interest")
    public ResponseEntity<InterestStudies> studyInfoInterestStudy() {
        return ResponseEntity.ok(studyService.studyInfoInterestStudy(SecurityUtil.getCurrentMemberId()));
    }
}
