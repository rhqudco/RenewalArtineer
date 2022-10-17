package com.artineer.artineer.repository.noticeComment;

import com.artineer.artineer.domain.NoticeComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeCommentJpaRepositoryImpl implements NoticeCommentJpaRepository{

    private final EntityManager em;

    @Override
    public NoticeComment save(NoticeComment noticeComment) {
        em.persist(noticeComment);
        return noticeComment;
    }

    @Override
    public List<NoticeComment> findByNoticeNo(Long no) {
        return em.createQuery("select nc from NoticeComment nc where nc.notice.no = :no", NoticeComment.class)
                .setParameter("no", no)
                .getResultList();
    }
}