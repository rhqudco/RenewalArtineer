package com.artineer.artineer.repository;

import com.artineer.artineer.domain.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepositoryImpl implements MeetingRepository{

    private final EntityManager em;

    @Override
    public List<Meeting> findAll() {
        return em.createQuery("Select m From Meeting m", Meeting.class)
                .getResultList();
    }

    @Override
    public void save(Meeting meeting) {
        if(meeting.getNo() == null) {
            em.persist(meeting);
            // no 값이 없기 때문에 (신규 등록이기 때문에) 신규 등록 역할
        } else {
            em.merge(meeting);
            // no 값이 있기 때문에 (중복 등록이기 때문에) 업데이트 역할
        }
    }

    @Override
    public List<Meeting> findByTitle(String title) {
        return em.createQuery("Select m From Meeting m where m.title =: title ", Meeting.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public void remove(Long no) {
        em.remove(em.find(Meeting.class, no));
    }
}
