package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeJpaRepository {

    private final EntityManager em;

    @Override
    public Notice save(Notice notice) {
        em.persist(notice);
        return notice;
    }
}