package project.study_with_me.domain.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.study_with_me.domain.study.entity.StudyJoin;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyJoinRepository extends JpaRepository<StudyJoin, Long> {

    Optional<StudyJoin> findByStudyIdAndJoinMemberId(Long studyId, Long joinMemberId);

    List<StudyJoin> findByJoinMemberId(Long joinMemberId);

    List<StudyJoin> findByGroupLeaderId(Long groupLeaderId);
}
