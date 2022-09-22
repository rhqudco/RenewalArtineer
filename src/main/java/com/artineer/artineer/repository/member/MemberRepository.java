package com.artineer.artineer.repository.member;

import com.artineer.artineer.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNo(Long no);
    List<Member> findById(String id);
    List<Member> findAll();
}