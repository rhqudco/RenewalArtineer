package com.artineer.artineer.repository.member;

import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.domain.Member;

import java.util.Optional;

public interface MemberJpaRepository {
    void save(Member member);

}