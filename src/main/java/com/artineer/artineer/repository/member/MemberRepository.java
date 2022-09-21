package com.artineer.artineer.repository.member;

import com.artineer.artineer.domain.Member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);
    Member findOne(Long no);
    List<Member> findById(String id);
}
