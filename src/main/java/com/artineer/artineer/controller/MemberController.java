package com.artineer.artineer.controller;

import com.artineer.artineer.loginCheck.SessionConst;
import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.MemberLoginDto;
import com.artineer.artineer.controller.dto.MemberSaveDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.validator.BirthValidator;
import com.artineer.artineer.validator.PhoneValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private static final Long adminLevel  = 2L;

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;
    private final BirthValidator birthValidator;
    private final PhoneValidator phoneValidator;

    @GetMapping("/members/join")
    public String save(Model model) {
        model.addAttribute("form", new MemberSaveDto());
        return "join/joinForm";
    }

    @PostMapping("/members/join")
    public String join(@Validated @ModelAttribute("form") MemberSaveDto dto,
                       BindingResult bindingResult) {


        birthValidator.validate(dto.getBirth(), bindingResult);
        phoneValidator.validate(dto.getPhone(), bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "join/joinForm";
        }

        // 이메일 조합
        StringBuffer sb = new StringBuffer();
        sb.append(dto.getEmailId());
        sb.append("@");
        sb.append(dto.getEmailDomain());
        String memberEmail = sb.toString();

        Birth memberBirth = Birth.createBirth(dto.getBirth().getYear(), dto.getBirth().getMonth(), dto.getBirth().getDay());

        Phone memberPhone = Phone.createPhone(dto.getPhone().getFirstNumber(), dto.getPhone().getMiddleNumber(), dto.getPhone().getLastNumber());

        // 성공 로직
        Member saveMember = Member.createMember(dto.getId(),
                webSecurityConfig.getPasswordEncoder().encode(dto.getPassword()),
                dto.getName(), memberBirth, memberEmail, memberPhone,
                dto.getGender(), dto.getGeneration(), "1");

        memberService.join(saveMember);

        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("form", new MemberLoginDto());
        return "login/loginForm";
    }

    @PostMapping("/members/login")
    public String login(@Validated @ModelAttribute("form") MemberLoginDto dto,
                      BindingResult bindingResult, HttpServletRequest request,
                      @RequestParam(defaultValue = "/") String redirectURL) {

        // 빈값 검증
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "login/loginForm";
        }

        Member loginMember = memberService.validLogin(dto.getId(), dto.getPassword());

        // 아이디 비밀번호 검증
        if (loginMember == null) {
            bindingResult.reject("checkIdPw", null);
            return "login/loginForm";
        }

        // 성공 로직
        // 세션이 있으면 세션 반환
        HttpSession session = request.getSession();
        // 없으면 신규 세션 생성
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }
}