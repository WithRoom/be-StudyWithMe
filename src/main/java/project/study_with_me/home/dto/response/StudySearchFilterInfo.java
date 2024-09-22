package project.study_with_me.home.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.study_with_me.home.dto.HomeStudyInfo;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudySearchFilterInfo {

    private List<HomeStudyInfo> homeStudyInfoList;

}
