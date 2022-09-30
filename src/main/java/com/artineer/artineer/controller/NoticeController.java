package com.artineer.artineer.controller;

import com.artineer.artineer.controller.dto.WriteSaveDto;
import com.artineer.artineer.controller.dto.member.MemberFindDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
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

    @GetMapping("/notice/write")
    public String writeNotice(Model model) {
        model.addAttribute("form", new WriteSaveDto());
        return "notice/noticeWriteForm";
    }

    @PostMapping("/notice/write")
    public String saveNotice(@Validated @ModelAttribute("form") WriteSaveDto dto,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             HttpSession session) {

//        if (bindingResult.hasErrors()) {
//            log.info("errors = {}", bindingResult);
//            return "notice/noticeWriteForm";
//        }

        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberNo = member.getNo();
        Member loginMember = memberService.findMember(memberNo).get(0);

//        Notice writeNotice = Notice.writeNotice(loginMember, LocalDateTime.now(), dto.getTitle(),
//                dto.getDetail(), 0L);
//
//        Notice saveNotice = noticeService.saveNotice(writeNotice);
//
//        redirectAttributes.addAttribute("noticeNo", saveNotice.getNo());

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
    public void postImage(MultipartFile[] uploadFile){

        String uploadFolder = "/Users/gobyeongchae/Desktop/fileUploadV1";

        /* 추가된 부분 ......... */
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String formatDate = sdt.format(date);

        String datePath = formatDate.replace("-", File.separator);

        File uploadPath = new File(uploadFolder);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }
        /*..........*/

        for (MultipartFile multipartFile : uploadFile) {

            String uploadFileName = multipartFile.getOriginalFilename();

            /* 변경 위치 ............. */
            String uuid = UUID.randomUUID().toString();
            uploadFileName = uuid + "_" + uploadFileName;

            File saveFile = new File(uploadPath, uploadFileName);
            /*.................*/

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @GetMapping("/display")
    public ResponseEntity<byte[]> showImageGET(@RequestParam("fileName") String fileName) {
        log.info("Controller showImageGET");

        log.info("fileName" + fileName);

        File file = new File("/Users/gobyeongchae/Desktop/fileUploadV1/" + fileName);

        ResponseEntity<byte[]> result = null;

        try {

            HttpHeaders header = new HttpHeaders();

        /*
        Files.probeContentType() 해당 파일의 Content 타입을 인식(image, text/plain ...)
        없으면 null 반환

        file.toPath() -> file 객체를 Path객체로 변환

        */
            header.add("Content-type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

//    ALTER TABLE NOTICE ALTER column DETAIL TEXT;