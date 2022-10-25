package com.artineer.artineer.repository.notice;

import com.artineer.artineer.controller.dto.notice.NoticeCondition;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.QNotice;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.artineer.artineer.domain.QMember.member;
import static com.artineer.artineer.domain.QNotice.notice;
import static org.springframework.util.StringUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeJpaRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Notice> searchNoticePage(NoticeCondition condition, Pageable pageable) {

        List<Notice> content = queryFactory
                .selectFrom(notice)
                .leftJoin(notice.member, member)
                .where(
                        idContains(condition.getId()),
                        titleContains(condition.getTitle())
                )
                .orderBy(notice.no.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Notice> countQuery = queryFactory
                .selectFrom(notice)
                .leftJoin(notice.member, member)
                .where(
                        idContains(condition.getId()),
                        titleContains(condition.getTitle())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression idContains(String id) {
        return isEmpty(id) ? null : notice.member.id.contains(id);
    }

    private BooleanExpression titleContains(String title) {
        return isEmpty(title) ? null : notice.title.contains(title);
    }
}