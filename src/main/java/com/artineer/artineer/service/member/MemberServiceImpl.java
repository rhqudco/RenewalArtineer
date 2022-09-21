package com.artineer.artineer.service.member;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final WebSecurityConfig webSecurityConfig;

    @Override
    @Transactional
    public Member join(Member member) {
        validateDuplicateMemberId(member);
        memberRepository.save(member);
        return member;
    }

    @Override
    public List<Member> findMember(Long no) {
        return memberRepository.findByNo(no);
    }

    @Override
    public Member validLogin(String id, String password) {
        validateExistentId(id);
        List<Member> findMember = memberRepository.findById(id);
        Member member = findMember.get(0);
        if (validateEqualPassword(password, member.getPassword())) {
            return member;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean validateEqualPassword(String rawPassword, String encodedPassword) {
        return webSecurityConfig.getPasswordEncoder().matches(rawPassword, encodedPassword);

    }

    // 로그인시 아이디 존재유무 검사
    private void validateExistentId(String id) {
        List<Member> findMember = memberRepository.findById(id);
        if(findMember.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }
    }

    // 아이디 중복 검사
    private void validateDuplicateMemberId(Member member) {
        List<Member> findMember = memberRepository.findById(member.getId());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }
}