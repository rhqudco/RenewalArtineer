package com.artineer.artineer.repository;

import com.artineer.artineer.domain.NoticeComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NoticeCommentRepositoryImpl implements NoticeCommentRepository{

    private final EntityManager em;

    @Override
    public void save(NoticeComment noticeComment) {
        if (noticeComment.getNoticeNo() == null) {
            em.persist(noticeComment);
        }
        else {
            em.merge(noticeComment);
        }
    }

    @Override
    public void remove(Long no) {
        em.remove(em.find(NoticeComment.class, no));
    }
}