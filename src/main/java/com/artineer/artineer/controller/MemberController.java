package com.artineer.artineer.controller;

import com.artineer.artineer.common.MailService;
import com.artineer.artineer.controller.dto.member.MemberFindDto;
import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.domain.Level;
import com.artineer.artineer.loginCheck.SessionConst;
import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.controller.dto.member.MemberLoginDto;
import com.artineer.artineer.controller.dto.member.MemberSaveDto;
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

import javax.mail.MessagingException;
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
    private final MailService mailService;

    // 회원가입
    @GetMapping("/members/join")
    public String memberJoinForm(Model model) {
        model.addAttribute("form", new MemberSaveDto());
        return "member/joinForm";
    }

    @PostMapping("/members/join")
    public String memberJoin(@Validated @ModelAttribute("form") MemberSaveDto dto,
                       BindingResult bindingResult) {


        birthValidator.validate(dto.getBirth(), bindingResult);
        phoneValidator.validate(dto.getPhone(), bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "member/joinForm";
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
                dto.getGender(), dto.getGeneration(), Level.USER);

        memberService.join(saveMember);

        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("form", new MemberLoginDto());
        return "member/loginForm";
    }

    @PostMapping("/members/login")
    public String login(@Validated @ModelAttribute("form") MemberLoginDto dto,
                      BindingResult bindingResult, HttpServletRequest request,
                      @RequestParam(defaultValue = "/") String redirectURL) {

        // 빈값 검증
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "member/loginForm";
        }

        Member loginMember = memberService.validLogin(dto.getId(), dto.getPassword());

        // 아이디 비밀번호 검증
        if (loginMember == null) {
            bindingResult.reject("checkIdPw", null);
            return "member/loginForm";
        }

        // 성공 로직
        // 세션이 있으면 세션 반환
        HttpSession session = request.getSession();
        // 없으면 신규 세션 생성
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/members/find/account")
    public String findAccountForm(Model model) {
        model.addAttribute("form", new MemberFindDto());
        return "member/findAccountForm";
    }


    @ResponseBody
    @PostMapping("/members/find/account/id")
    public String findAccountId(@RequestParam("memberName") String memberName,
                                @RequestParam("emailId") String emailId,
                                @RequestParam("emailDomain") String emailDomain) {

        if (memberName.equals("") || emailId.equals("") || emailDomain.equals("")) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append(emailId);
        sb.append("@");
        sb.append(emailDomain);
        String memberEmail = sb.toString();

        Member member = memberService.findAccountId(memberName, memberEmail);
        if (member == null) {
            return "위 정보로 가입된 회원을 찾을 수 없습니다.";
        }
        return member.getId();
    }

    @ResponseBody
    @PostMapping("/members/find/account/pw")
    public String findAccountPw(@RequestParam("memberId") String memberId,
                                @RequestParam("emailId") String emailId,
                                @RequestParam("emailDomain") String emailDomain) {
        // 빈 값 입력시
        if (memberId.equals("") || emailId.equals("") || emailDomain.equals("")) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        sb.append(emailId);
        sb.append("@");
        sb.append(emailDomain);
        String memberEmail = sb.toString();

        // 정보 없을 때
        Member member = memberService.findAccountPw(memberId, memberEmail);
        if (member == null) {
            return "위 정보로 가입된 회원을 찾을 수 없습니다.";
        }

        try {
            // 성공 했을 때
            // temporaryPassword = 임시 비밀번호, mailService.sendMessage(memberId, memberEmail = 메일 보내는 메소드
            String temporaryPassword = mailService.sendMessage(memberId, memberEmail);
            // 비밀번호 바꾸기
            String encodePassword = webSecurityConfig.getPasswordEncoder().encode(temporaryPassword);
            memberService.updatePassword(member.getNo(), encodePassword);
            return member.getId();
        } catch (MessagingException e) {
            return "잠시 후 다시 시도해 주세요.";
        }

        // 텍스트만 보내는 메소드
        //List<String> userMail = new ArrayList<>();
        //userMail.add(memberEmail);
    }

    @GetMapping("/members/modify/{memberNo}")
    public void modifyMember(@PathVariable("memberNo") Long memberNo) {
        Member findMember = memberService.findMember(memberNo).get(0);
        MemberModifyDto.modifyMember(findMember.getId(), findMember.getName(), findMember.getBirth(),
                findMember.getEmail(), findMember.getPhone(), findMember.getGender(), findMember.getGeneration());
    }
}