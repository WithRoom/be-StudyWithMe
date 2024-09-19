package project.study_with_me.home.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.study_with_me.home.dto.response.HomeInfoResponseDto;
import project.study_with_me.home.utils.HomeUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final HomeUtils homeUtils;

    public HomeInfoResponseDto getHomeInfo(Long memberId) {
        HomeInfoResponseDto homeInfoResponseDto = new HomeInfoResponseDto();

        // 스터디 조회
        homeInfoResponseDto = homeUtils.searchStudy(homeInfoResponseDto, memberId);

        // 사용자 조회
        homeInfoResponseDto = homeUtils.searchMember(homeInfoResponseDto, memberId);

        // 참여중 스터디 조회
        homeInfoResponseDto = homeUtils.searchJoinStudy(homeInfoResponseDto, memberId);

        return homeInfoResponseDto;
    }
}
