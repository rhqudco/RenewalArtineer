package com.artineer.artineer.service;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.controller.dto.member.MemberSaveDto;
import com.artineer.artineer.domain.Level;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import com.artineer.artineer.service.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

//    @BeforeEach
//    public void init() {
//        Member member = new Member("admin", webSecurityConfig.getPasswordEncoder().encode("1"),
//                "rhqudco1204@naver.com");
//        memberService.join(member);
//    }

    @Test
    void saveTest() {
        MemberSaveDto memberDTO = new MemberSaveDto();
        Birth birth = new Birth("2022", "09", "21");
        Phone phone = new Phone("010", "1111", "1111");
        memberDTO.setId("artineer");
        memberDTO.setPassword(webSecurityConfig.getPasswordEncoder().encode("admin"));
        memberDTO.setName("artineer");
        memberDTO.setBirth(birth);
        memberDTO.setEmailId("rhqudco1204");
        memberDTO.setEmailDomain("@naver.com");
        memberDTO.setPhone(phone);
        memberDTO.setGender("1");
        memberDTO.setGeneration("3");

        Member member = new Member(memberDTO);
        Member createMember = memberService.join(member);

        List<Member> findMember = memberService.findMember(createMember.getNo());

        System.out.println("createMember = " + createMember);
        System.out.println("findMember = " + findMember);


        assertThat(createMember).isEqualTo(findMember.get(0));
    }

    @Test
    void findAllTest() {
        MemberSaveDto memberDTO = new MemberSaveDto();
        Birth birth = new Birth("2022", "09", "21");
        Phone phone = new Phone("010", "1111", "1111");
        memberDTO.setId("artineer");
        memberDTO.setPassword(webSecurityConfig.getPasswordEncoder().encode("admin"));
        memberDTO.setName("artineer");
        memberDTO.setBirth(birth);
        memberDTO.setEmailId("rhqudco1204");
        memberDTO.setEmailDomain("@naver.com");
        memberDTO.setPhone(phone);
        memberDTO.setGender("1");
        memberDTO.setGeneration("3");

        Member member = new Member(memberDTO);
        memberService.join(member);

        List<Member> members = memberService.findAll();

        for (Member member1 : members) {
            System.out.println("member1 = " + member1.getId());
        }
    }

    @Test
    void modifyTest() {
        Member findMember = memberService.findMember(1L).get(0);

        // 테스트를 위해 영속성 컨텍스트 비움
        em.flush();
        em.clear();

        String memberEmail = "asd@asd.com";
        // birth
        Birth birth = new Birth("1", "2", "3");
        // Phone
        Phone phone = new Phone("123", "1234", "4321");
        MemberModifyDto memberModifyDto = MemberModifyDto.modifyMemberDto("admin", "123", "123", birth, memberEmail, phone, "man", "1");
        memberService.modifyMember(1L, memberModifyDto);

        Member modifyFindMember = memberService.findMember(1L).get(0);
        System.out.println("===============================================================================");
        System.out.println("findMember.getName() = " + findMember.getName());
        System.out.println("memberModifyDto.getName() = " + memberModifyDto.getName());
        System.out.println("===============================================================================");
        System.out.println("findMember = " + findMember);
        System.out.println("modifyFindMember = " + modifyFindMember);
        System.out.println("===============================================================================");

        assertThat(findMember).isNotEqualTo(modifyFindMember);
        assertThat(findMember.getId()).isEqualTo(modifyFindMember.getId());
        assertThat(findMember.getName()).isNotEqualTo(modifyFindMember.getName());
        /*
        * JPA의 영속성 컨텍스트에선 같은 식별자(PK)를 가지는 객체는 동일성을 보장하기 때문에
        * em.flush, em.clear를 하지 않으면 findMember와 modifyFindMember가 같은 식별자라 findMember의 값도 변경된다.
        * 때문에 findMember를 찾은 후 em.flush, em.clear를 해서
        * 다른 객체인데도 수정을 통해 값을 수정하면 잘 수정이 되는 것을 확인할 수 있다.
        * */
    }
}