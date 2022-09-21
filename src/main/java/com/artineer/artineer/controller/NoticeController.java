package com.artineer.artineer.controller;

import com.artineer.artineer.common.FileUpload;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController {

//    private final NoticeService noticeService;
//    private final FileUpload fileUpload;
//
//    @GetMapping("/writeNotice")
//    public String noticeForm() {
//        return "fileupload/filetest";
//    }
//
//    @PostMapping("/writeNotice")
//    public String writeNotice(@ModelAttribute Notice notice, List<MultipartFile> files) throws IOException {
//        noticeService.saveNotice(notice);
//        fileUpload.fileUpload(files);
//        return "assd";
//    }

}
