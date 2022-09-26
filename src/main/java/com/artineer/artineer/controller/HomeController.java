package com.artineer.artineer.controller;

import com.artineer.artineer.loginCheck.Login;
import com.artineer.artineer.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index(@Login Member loginMember, Model model) {
        log.info("index Method");

        // 세션에 데이터가 없으면 home
        if (loginMember == null) {
            return "index";
        }
        log.info("loginMember = {}", loginMember.getId());
        // 세션이 유지되면 로그인으로 이동
        return "loginIndex";
    }

    @PostMapping("/members/logout")
    public String logout(HttpServletRequest request) {
        log.info("logout Method");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}