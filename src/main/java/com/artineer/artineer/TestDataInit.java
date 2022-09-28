package com.artineer.artineer;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        Member member = new Member("admin", webSecurityConfig.getPasswordEncoder().encode("1"),
                "rhqudco1204@naver.com");
        memberService.join(member);
    }
}