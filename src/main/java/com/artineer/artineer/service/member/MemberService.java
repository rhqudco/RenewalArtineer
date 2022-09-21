package com.artineer.artineer.service.member;

import com.artineer.artineer.domain.Member;

public interface MemberService {
    void join(Member member);
    Member findOne(Long memberNo);
    Member validLogin(String id, String password);
    boolean validateEqualPassword(String rawPassword, String encodedPassword);
}
