package com.artineer.artineer.controller.dto.noticeComment;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.NoticeComment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeCommentDto {
    private Long no;
    private Member member;
    private String detail;
    private LocalDateTime writeDate;
    private Notice notice;
    private NoticeComment parentComment;
    private List<NoticeCommentDto> childComments = new ArrayList<>();

    public NoticeCommentDto(Long no, Member member, String detail, LocalDateTime writeDate, Notice notice, NoticeComment parentComment) {
        this.no = no;
        this.member = member;
        this.detail = detail;
        this.writeDate = writeDate;
        this.notice = notice;
        this.parentComment = parentComment;
    }

    public static NoticeCommentDto convertCommentToDto(NoticeComment noticeComment) {
        return new NoticeCommentDto(noticeComment.getNo(), noticeComment.getMember(),
                noticeComment.getDetail(), noticeComment.getWriteDate(), noticeComment.getNotice(),
                noticeComment.getParentComment());
    }
}