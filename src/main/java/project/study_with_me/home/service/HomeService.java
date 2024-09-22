package project.study_with_me.home.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.study_with_me.domain.study.entity.Study;
import project.study_with_me.domain.study.repository.StudyRepository;
import project.study_with_me.domain.study.utils.DtoUtils;
import project.study_with_me.home.dto.HomeStudyInfo;
import project.study_with_me.home.dto.response.HomeInfoResponseDto;
import project.study_with_me.home.dto.response.StudySearchFilterInfo;
import project.study_with_me.home.utils.HomeUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeService {

    private final HomeUtils homeUtils;
    private final StudyRepository studyRepository;
    private final DtoUtils dtoUtils;

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

    public StudySearchFilterInfo searchStudies(String topic, String difficulty, String weekDay, String type,
                                               Boolean state, Long memberId) {
        StudySearchFilterInfo studySearchFilterInfo = new StudySearchFilterInfo();
        List<HomeStudyInfo> homeStudyInfoList = new ArrayList<>();

        List<Study> studyFilterList = studyRepository.findByFilters(topic, difficulty, weekDay, type, state);

        for (Study study : studyFilterList) {
            Boolean interest = dtoUtils.getInterest(memberId, study.getStudyId());
            homeStudyInfoList.add(studySearchFilterInfo.createHomeStudyInfo(study, interest));
        }

        studySearchFilterInfo.setHomeStudyInfoList(homeStudyInfoList);

        return studySearchFilterInfo;
    }
}
