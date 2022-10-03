package com.artineer.artineer.repository.notice;

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
    public List<NoticeComment> findByNoticeNo(Long no) {
        return em.createQuery("select nc from NoticeComment nc join fetch nc.notice n where n.no = :no", NoticeComment.class)
                .getResultList();
    }
}