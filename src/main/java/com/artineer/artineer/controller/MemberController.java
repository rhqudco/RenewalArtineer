package com.artineer.artineer.controller;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;

    @RequestMapping("/test/members")
    public void test() {
        Member member = new Member("id", "name");

        memberService.join(member);
    }
}