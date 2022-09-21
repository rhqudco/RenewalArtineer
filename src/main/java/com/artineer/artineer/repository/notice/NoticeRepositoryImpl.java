package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository{

    private final EntityManager em;

    @Override
    public List<Notice> findAll() {
        return em.createQuery("Select n From Notice n", Notice.class)
                .getResultList();
    }

    @Override
    public void save(Notice notice) {
        if(notice.getNo() == null) {
            em.persist(notice);
            // no 값이 없기 때문에 (신규 등록이기 때문에) 신규 등록 역할
        } else {
            em.merge(notice);
            // no 값이 있기 때문에 (중복 등록이기 때문에) 업데이트 역할
        }
    }

    @Override
    public List<Notice> findByTitle(String title) {
        return em.createQuery("Select n From Notice n where n.title =: title ", Notice.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public void remove(Long no) {
        em.remove(em.find(Notice.class, no));
    }
}