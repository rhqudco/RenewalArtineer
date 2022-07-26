package com.artineer.artineer.controller;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;

}
