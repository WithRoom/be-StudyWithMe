package project.study_with_me.domain.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.study_with_me.domain.study.entity.StudyMember;

import java.util.List;

@Repository
public interface StudyMemberRepository extends JpaRepository<StudyMember, Long> {

    List<StudyMember> findByMemberId(Long memberId);
}
