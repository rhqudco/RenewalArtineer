package com.artineer.artineer.controller;

import com.artineer.artineer.common.FileStore;
import com.artineer.artineer.common.WritingShowImage;
import com.artineer.artineer.controller.dto.WriteSaveDto;
import com.artineer.artineer.controller.dto.member.MemberFindDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.UploadFile;
import com.artineer.artineer.loginCheck.SessionConst;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.service.notice.NoticeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;
    private final MemberService memberService;
    private final FileStore fileStore;
    private final WritingShowImage writingShowImage;

    @GetMapping("/notice/write")
    public String writeNotice(Model model) {
        model.addAttribute("form", new WriteSaveDto());
        return "notice/noticeWriteForm";
    }

    @PostMapping("/notice/write")
    public String saveNotice(@Validated @ModelAttribute("form") WriteSaveDto dto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             HttpSession session) throws IOException {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "notice/noticeWriteForm";
        }

        Member sessionLogin = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberNo = sessionLogin.getNo();
        Member loginMember = memberService.findMember(memberNo).get(0);

        UploadFile uploadFile = fileStore.storeFile(dto.getUploadFile());

        Notice writeNotice = Notice.writeNotice(loginMember, LocalDateTime.now(), dto.getTitle(),
                dto.getDetail(), uploadFile, 0L);

        Notice saveNotice = noticeService.saveNotice(writeNotice);

        redirectAttributes.addAttribute("noticeNo", saveNotice.getNo());

        return "redirect:/notice/noticeView/{noticeNo}";
    }

    @GetMapping("/notice/noticeView/{noticeNo}")
    public String viewNotice(@PathVariable("noticeNo") Long no, Model model) {
        List<Notice> notices = noticeService.lookUpNotice(no);
        Notice notice = notices.get(0);
        model.addAttribute("item", notice);
        return "notice/noticeView";
    }

    @ResponseBody
    @PostMapping("/post/imageUpload")
    public File postImage(MultipartFile[] uploadFile){
        return writingShowImage.imagePost(uploadFile);
    }

    @ResponseBody
    @GetMapping("/display")
    public ResponseEntity<byte[]> showImageGET(@RequestParam("fileName") String fileName) {
        return writingShowImage.displayImage(fileName);
    }
}

//    ALTER TABLE NOTICE ALTER column DETAIL TEXT;