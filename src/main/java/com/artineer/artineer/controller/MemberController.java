package com.artineer.artineer.controller;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.MemberSaveForm;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;

    @GetMapping("/members/join")
    public void save(Model model) {
        model.addAttribute("form", new MemberSaveForm());

        // return ~~~
    }

    @PostMapping("/member/join")
    public void join(MemberSaveForm form) {
        Member saveMember = new Member(form.getId(),
                webSecurityConfig.getPasswordEncoder().encode(form.getPassword()),
                form.getName(), form.getBirth(), form.getEmail(),
                form.getPhone(), form.getGender(), form.getGeneration(), form.getLevel());
        memberService.join(saveMember);

        // return ~~~
    }
}