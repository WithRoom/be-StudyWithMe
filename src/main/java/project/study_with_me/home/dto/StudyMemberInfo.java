package project.study_with_me.home.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyMemberInfo {

    private Long studyId;
    private String studyImageUrl;
    private String title;
    private String startDay;
    private Boolean state;
}
