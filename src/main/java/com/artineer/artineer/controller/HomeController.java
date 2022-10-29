package com.artineer.artineer.controller;

import com.artineer.artineer.common.FileStore;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.loginCheck.Login;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final NoticeService noticeService;
    private final FileStore fileStore;


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

    @GetMapping("/attach/{boardNo}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long boardNo) throws MalformedURLException {
        List<Notice> notices = noticeService.lookUpNotice(boardNo);
        Notice notice = notices.get(0);
        String storeFileName = notice.getUploadFile().getStoreFileName();
//        log.info("storeFileName = {}", storeFileName);
        String uploadFileName = notice.getUploadFile().getUploadFileName();
//        log.info("uploadFileName={}", uploadFileName);

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
//        log.info("resource = {}", resource);

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
//        log.info("encodedUploadFileName = {}", encodedUploadFileName);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
//        log.info("contentDisposition = {}", contentDisposition);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    // test
    @GetMapping("/indexFile")
    public String indexFile() {
        return "/fragment/index";
    }
}