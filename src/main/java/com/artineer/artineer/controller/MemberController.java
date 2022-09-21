package com.artineer.artineer.controller;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;

}