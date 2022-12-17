package com.artineer.artineer.repository.member;

import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepositoryImpl implements MemberJpaRepository{

    private final EntityManager em;

    @Override
    @Transactional
    public void save(Member member) {
        em.persist(member);
    }

}