package project.study_with_me.domain.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    List<Study> findByGroupLeader(Long groupLeader);

    @Query("SELECT s FROM Study s JOIN s.schedule sc ORDER BY sc.startDay DESC")
    List<Study> findAllByOrderByStartDayDesc();
}
