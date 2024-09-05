package project.study_with_me.domain.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.study_with_me.domain.study.entity.StudyInterest;

import java.util.List;

@Repository
public interface StudyInterestRepository extends JpaRepository<StudyInterest, Long> {

    List<StudyInterest> findByMemberId(Long memberId);
}
