package project.study_with_me.domain.study.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.study_with_me.auth.jwt.utils.SecurityUtil;
import project.study_with_me.domain.study.dto.request.*;
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
    public ResponseEntity<Boolean> createStudy(@RequestBody CreateStudyRequestDto createStudyRequestDto) {
        return ResponseEntity.ok(studyService.createStudy(createStudyRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "관심 스터디 등록 및 해제")
    @PostMapping("/interest")
    public ResponseEntity<Boolean> interestStudy(@RequestBody StudyInterestRequestDto studyInterestRequestDto) {
        return ResponseEntity.ok(studyService.interestStudy(studyInterestRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 참여 신청")
    @PostMapping("/join")
    public ResponseEntity<Boolean> joinStudy(@RequestBody StudyJoinRequestDto studyJoinRequestDto) {
        return ResponseEntity.ok(studyService.joinStudy(studyJoinRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 참여 수락 및 거절")
    @PostMapping("/response-join")
    public ResponseEntity<Boolean> responseJoinStudy(@RequestBody JoinStudyRequestDto responseJoinStudyRequestDto) {
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

    @Operation(summary = "스터디 상세 조회")
    @PostMapping("/info/detail")
    public ResponseEntity<StudyDetailInfoResponseDto> studyDetailInfo(@RequestBody StudyDetailRequestDto studyDetailRequestDto) {
        return ResponseEntity.ok(studyService.studyDetailInfo(studyDetailRequestDto));
    }

    @Operation(summary = "스터디 마감 신청")
    @PostMapping("/finish")
    public ResponseEntity<Boolean> studyFinish(@RequestBody StudyFinishRequestDto studyFinishRequestDto) {
        return ResponseEntity.ok(studyService.studyFinish(studyFinishRequestDto, SecurityUtil.getCurrentMemberId()));
    }

    @Operation(summary = "스터디 삭제")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> studyDelete(@RequestBody StudyDeleteRequestDto studyDeleteRequestDto) {
        return ResponseEntity.ok(studyService.studyDelete(studyDeleteRequestDto, SecurityUtil.getCurrentMemberId()));
    }
}