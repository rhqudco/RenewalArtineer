package com.artineer.artineer.repository.noticeComment;

import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.domain.QNoticeComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.artineer.artineer.domain.QNoticeComment.*;

@Repository
@RequiredArgsConstructor
public class NoticeCommentJpaRepositoryImpl implements NoticeCommentJpaRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public NoticeComment save(NoticeComment noticeComment) {
        em.persist(noticeComment);
        return noticeComment;
    }

    @Override
    public List<NoticeComment> findByNoticeNo(Long noticeNo) {
        return queryFactory
                .selectFrom(noticeComment)
                .leftJoin(noticeComment.parentComment)
                .fetchJoin()
                .where(noticeComment.notice.no.eq(noticeNo))
                .orderBy(
                        noticeComment.parentComment.no.asc().nullsFirst(),
                        noticeComment.no.asc()
                )
                .fetch();
    }
}