package project.study_with_me.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.study_with_me.domain.entity.Study;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
}
