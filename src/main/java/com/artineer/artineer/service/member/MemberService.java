package com.artineer.artineer.service.member;

import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.exception.UserNotMatchedException;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member join(Member member);
    List<Member> findMember(Long no);
    Member validLogin(String id, String password);
    boolean validateEqualPassword(String rawPassword, String encodedPassword);
    List<Member> findAll();
    Member findAccountId(String name, String email);
    Member findAccountPw(String id, String email);
    void updatePassword(Long no, String password);
    void modifyMember(Long memberNo, MemberModifyDto memberModifyDto);
    void deleteMember(Long memberNo);
    void validationDuplicateMemberId(String id);

    Member loadMemberByMemberId(String id) throws UserNotMatchedException;
}