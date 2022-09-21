package com.artineer.artineer.repository.member;

import com.artineer.artineer.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository{

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Member findOne(Long no) {
        return em.find(Member.class, no);
    }

    @Override
    public List<Member> findById(String id) {
        return em.createQuery("Select m From Member m where m.id =:id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }
}
