package com.artineer.artineer.service.member;

import com.artineer.artineer.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member join(Member member);

    List<Member> findMember(Long no);
    Member validLogin(String id, String password);
    boolean validateEqualPassword(String rawPassword, String encodedPassword);
    List<Member> findAll();
}
