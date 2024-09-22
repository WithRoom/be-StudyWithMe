package project.study_with_me.home.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.study_with_me.domain.study.entity.Study;

import java.util.List;

import static project.study_with_me.domain.study.entity.QSchedule.schedule;
import static project.study_with_me.domain.study.entity.QStudy.study;

@RequiredArgsConstructor
@Repository
public class StudyRepositoryImpl implements StudyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Study> findByFilters(String topic, String difficulty, String weekDay, String type, Boolean state) {
        return queryFactory.selectFrom(study)
                .leftJoin(study.schedule, schedule)
                .where(
                        eqTopic(topic),
                        eqDifficulty(difficulty),
                        eqWeekDay(weekDay),
                        eqType(type),
                        eqState(state)
                )
                .fetch();
    }

    private BooleanExpression eqTopic(String topic) {
        return topic != null ? study.topic.eq(topic) : null;
    }

    private BooleanExpression eqDifficulty(String difficulty) {
        return difficulty != null ? study.difficulty.eq(difficulty) : null;
    }

    private BooleanExpression eqWeekDay(String weekDay) {
        return weekDay != null ? study.schedule.weekDay.eq(weekDay) : null;
    }

    private BooleanExpression eqType(String type) {
        return type != null ? study.type.eq(type) : null;
    }

    private BooleanExpression eqState(Boolean state) {
        return state != null ? study.schedule.state.eq(state) : null;
    }
}
