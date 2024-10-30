package project.study_with_me.home.repository;

import project.study_with_me.domain.study.entity.Study;

import java.util.List;

public interface StudyRepositoryCustom {
    List<Study> findByFilters(String topic, String difficulty, String weekDay, String type, Boolean state);

    List<Study> findByTitles(String title);
}
