package com.artineer.artineer.controller;

import com.artineer.artineer.common.FileStore;
import com.artineer.artineer.common.WritingShowImage;
import com.artineer.artineer.controller.dto.WriteSaveDto;
import com.artineer.artineer.controller.dto.notice.NoticePageDto;
import com.artineer.artineer.controller.dto.notice.SubNoticeCommentDto;
import com.artineer.artineer.controller.dto.noticeComment.NoticeCommentDto;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.domain.UploadFile;
import com.artineer.artineer.loginCheck.SessionConst;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.service.noticeComment.NoticeCommentService;
import com.artineer.artineer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;
    private final MemberService memberService;
    private final FileStore fileStore;
    private final WritingShowImage writingShowImage;
    private final NoticeCommentService noticeCommentService;

    @GetMapping("/notice")
    public String viewNoticeBoard(@PageableDefault(size = 15, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model) {
        Page<Notice> allNotice = noticeService.findAllNotice(pageable);

        Page<NoticePageDto> pageDto = allNotice.map(m -> NoticePageDto.createNoticePageDto(m.getNo(), m.getMember().getId(), m.getWriteDate(), m.getTitle(), m.getView()));
        int totalPages = pageDto.getTotalPages();// 총 페이지 수

        model.addAttribute("pageDto", pageDto);
        model.addAttribute("totalPages", totalPages);

        return "notice/noticeBoard";
    }

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

        // member 찾기
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
    public String viewNotice(@PathVariable("noticeNo") Long noticeNo, Model model,
                             HttpServletRequest request) {
        List<Notice> notices = noticeService.lookUpNotice(noticeNo);
        if (notices.size() == 0) {
            return "redirect:/notice";
        }
        Notice notice = notices.get(0);
        noticeService.updateNoticeView(noticeNo);
        List<NoticeComment> noticeComments = noticeCommentService.findAllCommentOfNotice(noticeNo);

        model.addAttribute("notice", notice);
        model.addAttribute("noticeComments", noticeComments);
        model.addAttribute("form", new SubNoticeCommentDto());
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

    @ResponseBody // ajax 예정
    @PostMapping("/writeComment")
    public void writeComment(@RequestParam("notice-no") Long noticeNo, @RequestParam("comment") String comment, HttpSession session) {
        Notice notice = noticeService.lookUpNotice(noticeNo).get(0);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        NoticeComment writeComment = NoticeComment.writeComment(member, comment, LocalDateTime.now(), notice);
        noticeCommentService.save(writeComment);
    }

    @PostMapping("/writeSubCommentForm/{noticeNo}")
    public String writeCommentForm(@ModelAttribute("form") SubNoticeCommentDto dto,
                                   @PathVariable("noticeNo") Long noticeNo, HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        log.info("dto.getParentNo() = {}", dto.getParentNo());
        log.info("dto.getDetail() = {}", dto.getDetail());
        Notice notice = noticeService.lookUpNotice(noticeNo).get(0);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        NoticeComment parentComment = noticeCommentService.lookUpComment(dto.getParentNo()).get(0);
        NoticeComment writeComment = NoticeComment.writeChildComment(member, dto.getDetail(), LocalDateTime.now(), notice, parentComment);

        noticeCommentService.save(writeComment);

        redirectAttributes.addAttribute("noticeNo", noticeNo);
        return "redirect:/notice/noticeView/{noticeNo}";
    }

    // 댓글에 답글
    @ResponseBody // ajax 예정
    @PostMapping("/writeChildComment")
    public void writeChildComment(@RequestParam("notice-no") Long noticeNo, @RequestParam("comment") String comment,
                                  @RequestParam("parent-comment") Long parentComment, HttpSession session) {
        Notice notice = noticeService.lookUpNotice(noticeNo).get(0);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        NoticeComment parentCom = noticeCommentService.lookUpComment(parentComment).get(0);
        NoticeComment writeComment = NoticeComment.writeChildComment(member, comment, LocalDateTime.now(), notice, parentCom);
        noticeCommentService.save(writeComment);
    }
}

//    ALTER TABLE NOTICE ALTER column DETAIL TEXT;