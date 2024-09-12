package project.study_with_me.domain.study.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyGroupLeader {

    private String preferredArea;   // 선호 지역
    private String name;
    private String profileImage;
}
