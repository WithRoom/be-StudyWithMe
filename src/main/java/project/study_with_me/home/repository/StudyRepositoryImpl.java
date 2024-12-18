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
                        containsTopic(topic),
                        eqDifficulty(difficulty),
                        containsWeekDay(weekDay),
                        eqType(type)
                )
                .fetch();
    }

    @Override
    public List<Study> findByTitles(String title) {
        return queryFactory.selectFrom(study)
                .leftJoin(study.schedule, schedule)
                .where(containsTitle(title))
                .fetch();
    }

    private BooleanExpression containsTopic(String topic) {
        return topic != null ? study.topic.contains(topic) : null;
    }

    private BooleanExpression eqDifficulty(String difficulty) {
        return difficulty != null ? study.difficulty.eq(difficulty) : null;
    }

    private BooleanExpression containsWeekDay(String weekDay) {
        return weekDay != null ? study.schedule.weekDay.contains(weekDay) : null;
    }

    private BooleanExpression eqType(String type) {
        return type != null ? study.type.eq(type) : null;
    }

    private BooleanExpression containsTitle(String title) {
        return title != null ? study.title.contains(title) : null;
    }
}
