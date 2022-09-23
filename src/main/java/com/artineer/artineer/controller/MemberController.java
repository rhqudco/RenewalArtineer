package com.artineer.artineer.controller;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.MemberSaveDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import com.artineer.artineer.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;

    @GetMapping("/members/join")
    public String save(Model model) {
        model.addAttribute("form", new MemberSaveDto());
        return "join/joinForm";
    }

    @PostMapping("/members/join")
    public String join(@Validated @ModelAttribute("member") MemberSaveDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "join/joinForm";
        }

        log.info("id = {}", dto.getId());

        Birth birth = new Birth(dto.getBirth().getYear(), dto.getBirth().getMonth(), dto.getBirth().getDay());
        log.info("year = {} month = {} day = {}", dto.getBirth().getYear(), dto.getBirth().getMonth(), dto.getBirth().getDay());
        Phone phone = new Phone(dto.getPhone().getFirstNumber(), dto.getPhone().getMiddleNumber(), dto.getPhone().getLastNumber());

        // 성공 로직
        Member saveMember = new Member(dto.getId(),
                webSecurityConfig.getPasswordEncoder().encode(dto.getPassword()),
                dto.getName(), birth, dto.getEmail(),
                phone, dto.getGender(), dto.getGeneration(), dto.getLevel());
        Member member = memberService.join(saveMember);

        return "redirect:/";
    }
}