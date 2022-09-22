package com.artineer.artineer.repository.member;

import com.artineer.artineer.domain.Member;

public interface MemberJpaRepository {
    void save(Member member);
}