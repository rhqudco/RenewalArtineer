package com.artineer.artineer.controller;

import com.artineer.artineer.controller.dto.WriteSaveDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice/write")
    public void writeNotice(Model model) {
        model.addAttribute("form", new WriteSaveDto());
    }

//    @PostMapping("/notice/write")
//    public void saveNotice(@Validated @ModelAttribute("form") WriteSaveDto dto,
//                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//
//        if (bindingResult.hasErrors()) {
//            log.info("errors = {}", bindingResult);
////            return "join/joinForm";
//        }
//
//        Notice writeNotice = Notice.writeNotice(new Member(), LocalDateTime.now(), dto.getTitle(),
//                dto.getDetail(), dto.getFileName(), dto.getImageName(), 0L);
//
//        noticeService.saveNotice(writeNotice);
//    }
}