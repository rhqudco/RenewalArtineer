package com.artineer.artineer.controller;

import com.artineer.artineer.common.FileStore;
import com.artineer.artineer.common.WritingShowImage;
import com.artineer.artineer.controller.dto.WriteSaveDto;
import com.artineer.artineer.controller.dto.notice.NoticeCondition;
import com.artineer.artineer.controller.dto.notice.NoticePageDto;
import com.artineer.artineer.controller.dto.notice.NoticeViewDto;
import com.artineer.artineer.controller.dto.noticeComment.SubNoticeCommentDto;
import com.artineer.artineer.controller.dto.noticeComment.NoticeCommentDto;
import com.artineer.artineer.domain.*;
import com.artineer.artineer.exception.UserMatchedException;
import com.artineer.artineer.loginCheck.SessionConst;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.service.noticeComment.NoticeCommentService;
import com.artineer.artineer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

/*    @GetMapping("/notice")
    public String viewNoticeBoard(@PageableDefault(size = 15, sort = "no", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model) {
        Page<Notice> allNotice = noticeService.findAllNotice(pageable);

        Page<NoticePageDto> pageDto = allNotice.map(m -> NoticePageDto.createNoticePageDto(m.getNo(), m.getMember().getId(), m.getWriteDate(), m.getTitle(), m.getView()));
        int totalPages = pageDto.getTotalPages();// 총 페이지 수

        model.addAttribute("pageDto", pageDto);
        model.addAttribute("totalPages", totalPages);

        return "noticeBoard(UnUse)";
    }*/

    @GetMapping("/notice")
    public String viewNoticePageBoard(@PageableDefault(size = 10) Pageable pageable, NoticeCondition condition, Model model,
                                      @RequestParam(value = "selectorParam", required = false, defaultValue = "") String selectorParam,
                                      @RequestParam(value = "parameter", required = false, defaultValue = "") String parameter) {

        if (selectorParam.equals("writer")) {
            condition.setId(parameter);
        } else {
            condition.setTitle(parameter);
        }

        Page<NoticePageDto> noticeTitleOrId = noticeService.findNoticeTitleOrId(pageable, condition);

        int totalPage = noticeTitleOrId.getTotalPages();

        model.addAttribute("pageDto", noticeTitleOrId);
        model.addAttribute("maxPage", 10);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("selectorParam", selectorParam);
        model.addAttribute("parameter", parameter);

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
    public String viewNotice(@PathVariable("noticeNo") Long noticeNo, Model model) {

        List<Notice> notices = noticeService.lookUpNotice(noticeNo);
        if (notices.size() == 0) {
            // 삭제된 글 alert 띄우기.
            return "redirect:/notice";
        }
        NoticeViewDto notice = NoticeViewDto.convertNoticeDto(notices.get(0));
        noticeService.updateNoticeView(noticeNo);
        List<NoticeCommentDto> noticeComments = noticeCommentService.findAllCommentOfNotice(noticeNo);

        model.addAttribute("notice", notice);
        model.addAttribute("noticeComments", noticeComments);
        model.addAttribute("form", new SubNoticeCommentDto());
        return "notice/noticeView";
    }

    @ResponseBody
    @PostMapping("/post/imageUpload")
    public File postImage(MultipartFile[] uploadFile) {
        return writingShowImage.imagePost(uploadFile);
    }

    @ResponseBody
    @GetMapping("/display")
    public ResponseEntity<byte[]> showImageGET(@RequestParam("fileName") String fileName) {
        return writingShowImage.displayImage(fileName);
    }

    @PostMapping("/deleteNotice/{noticeNo}")
    public String deleteNotice(@PathVariable("noticeNo") Long noticeNo,
                               HttpSession session) throws UserMatchedException {

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Notice findNotice = noticeService.lookUpNotice(noticeNo).get(0);
        if (!findNotice.getMember().getNo().equals(loginMember.getNo())) {
            throw new UserMatchedException("본인만 삭제할 수 있습니다.");
        }
        noticeService.deleteNotice(noticeNo);
        noticeCommentService.deleteAllCommentByNotice(noticeNo);
        return "redirect:/notice";
    }

    /*
     * 댓글 작섣
     * */
    @ResponseBody
    @PostMapping("/writeComment")
    public String writeComment(@RequestParam("notice-no") Long noticeNo,
                               @RequestParam("comment") String comment,
                               HttpSession session) {

        Notice notice = noticeService.lookUpNotice(noticeNo).get(0);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        NoticeComment writeComment = NoticeComment.writeComment(member, comment, LocalDateTime.now(), notice, checkDeleted.isNotDeleted);
        noticeCommentService.save(writeComment);
        return Long.toString(noticeNo);
    }

    /*
     * 대댓글 작섣
     * */
    @PostMapping("/writeSubCommentForm/{noticeNo}")
    public String writeCommentForm(@ModelAttribute("form") SubNoticeCommentDto dto,
                                   @PathVariable("noticeNo") Long noticeNo, HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        Notice notice = noticeService.lookUpNotice(noticeNo).get(0);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        NoticeComment parentComment = noticeCommentService.lookUpComment(dto.getParentNo()).get(0);
        NoticeComment writeComment = NoticeComment.writeChildComment(member, dto.getDetail(), LocalDateTime.now(), notice, parentComment, checkDeleted.isNotDeleted);

        noticeCommentService.save(writeComment);

        redirectAttributes.addAttribute("noticeNo", noticeNo);
        return "redirect:/notice/noticeView/{noticeNo}";
    }

    /*
    * 댓글 삭제(부모 댓글, 일반 댓글)
    * */
    @PostMapping("/deleteComment/{noticeNo}")
    public String deleteParentComment(@RequestParam(value = "parentNo") Long parentNo,
                                      @PathVariable("noticeNo") Long noticeNo,
                                      RedirectAttributes redirectAttributes,
                                      HttpSession session) throws UserMatchedException {

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (parentNo != null) { // 부모 인덱스가 들어오면
            NoticeComment comment = noticeCommentService.findByNo(parentNo).get(0); // 찾는다.
            if (comment.getChildComments().isEmpty()) { // 자식 댓글이 없으면
                if (loginMember.getNo().equals(comment.getMember().getNo())) { // 로그인 계정과 사용자가 일치하면.
                    noticeCommentService.deleteComment(parentNo); // 그냥 삭제
                } else {
                    throw new UserMatchedException("본인만 삭제할 수 있습니다.");
                }
            }
            else { // 자식 댓글이 있으면
                if (loginMember.getNo().equals(comment.getMember().getNo())) { // 로그인 계정과 사용자가 일치하면.
                    noticeCommentService.deleteCommentHaveChild(parentNo); // 상태를 삭제로 바꾸고 내용도 삭제되었다고 알림.
                } else {
                    throw new UserMatchedException("본인만 삭제할 수 있습니다.");
                }
            }
        }
        redirectAttributes.addAttribute("noticeNo", noticeNo);
        return "redirect:/notice/noticeView/{noticeNo}";
    }

    /*
    * 댓글 삭제(대댓글)
    * */
    @PostMapping("/deleteChildComment/{noticeNo}")
    public String deleteChildComment(@PathVariable("noticeNo") Long noticeNo,
                                     @RequestParam("childNo") Long childNo,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession session) throws UserMatchedException {

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        NoticeComment childComment = noticeCommentService.findByNo(childNo).get(0);
        if (loginMember.getNo().equals(childComment.getMember().getNo())) {
            noticeCommentService.deleteComment(childNo);
        } else {
            throw new UserMatchedException("본인만 삭제할 수 있습니다.");
        }

        redirectAttributes.addAttribute("noticeNo", noticeNo);
        return "redirect:/notice/noticeView/{noticeNo}";
    }
}