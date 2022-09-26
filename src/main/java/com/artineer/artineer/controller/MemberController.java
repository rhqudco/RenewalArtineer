package com.artineer.artineer.controller;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.MemberLoginDto;
import com.artineer.artineer.controller.dto.MemberSaveDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.validator.BirthValidator;
import com.artineer.artineer.validator.PhoneValidator;
import com.artineer.artineer.validator.marker.SignInMarker;
import com.artineer.artineer.validator.marker.SignUpMarker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

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
    public String join(@Validated(SignUpMarker.class) @ModelAttribute("form") MemberSaveDto dto,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {


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
    public String login(@Validated(SignInMarker.class) @ModelAttribute("form") MemberSaveDto dto,
                      BindingResult bindingResult, HttpServletRequest request) {

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
        HttpSession session = request.getSession();
        session.setAttribute("memberNo", loginMember.getNo());
        session.setAttribute("memberId", loginMember.getId());
        session.setAttribute("memberName", loginMember.getName());
        return "redirect:/";

    }
}