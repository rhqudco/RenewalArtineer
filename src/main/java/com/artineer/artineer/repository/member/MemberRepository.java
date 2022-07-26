package com.artineer.artineer.repository.member;

import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNo(Long no);
    List<Member> findById(String id);
    List<Member> findAll();
    List<Member> findByNameAndEmail(String id, String email);
    List<Member> findByIdAndEmail(String id, String email);
    void deleteByNo(Long no);
}