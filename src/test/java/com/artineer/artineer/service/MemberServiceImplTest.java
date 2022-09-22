package com.artineer.artineer.service;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.MemberSaveForm;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import com.artineer.artineer.service.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    MemberService memberService;

    @Test
    void saveTest() {
        MemberSaveForm memberDTO = new MemberSaveForm();
        Birth birth = new Birth("2022", "09", "21");
        Phone phone = new Phone("010", "1111", "1111");
        memberDTO.setId("artineer");
        memberDTO.setPassword(webSecurityConfig.getPasswordEncoder().encode("admin"));
        memberDTO.setName("artineer");
        memberDTO.setBirth(birth);
        memberDTO.setEmail("rhqudco1204@naver.com");
        memberDTO.setPhone(phone);
        memberDTO.setGender("1");
        memberDTO.setGeneration("3");
        memberDTO.setLevel("1");

        Member member = new Member(memberDTO);
        Member createMember = memberService.join(member);

        List<Member> findMember = memberService.findMember(createMember.getNo());

        System.out.println("createMember = " + createMember);
        System.out.println("findMember = " + findMember);


        assertThat(createMember).isEqualTo(findMember.get(0));
    }

    @Test
    void findAllTest() {
        MemberSaveForm memberDTO = new MemberSaveForm();
        Birth birth = new Birth("2022", "09", "21");
        Phone phone = new Phone("010", "1111", "1111");
        memberDTO.setId("artineer");
        memberDTO.setPassword(webSecurityConfig.getPasswordEncoder().encode("admin"));
        memberDTO.setName("artineer");
        memberDTO.setBirth(birth);
        memberDTO.setEmail("rhqudco1204@naver.com");
        memberDTO.setPhone(phone);
        memberDTO.setGender("1");
        memberDTO.setGeneration("3");
        memberDTO.setLevel("1");

        Member member = new Member(memberDTO);
        memberService.join(member);

        List<Member> members = memberService.findAll();

        for (Member member1 : members) {
            System.out.println("member1 = " + member1.getId());
        }
    }
}