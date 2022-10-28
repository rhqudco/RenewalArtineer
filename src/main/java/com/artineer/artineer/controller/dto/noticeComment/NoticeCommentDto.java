package com.artineer.artineer.controller.dto.noticeComment;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.domain.checkDeleted;
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
    private checkDeleted checkDeleted;

    public NoticeCommentDto(Long no, Member member, String detail, LocalDateTime writeDate, Notice notice, NoticeComment parentComment, checkDeleted checkDeleted) {
        this.no = no;
        this.member = member;
        this.detail = detail;
        this.writeDate = writeDate;
        this.notice = notice;
        this.parentComment = parentComment;
        this.checkDeleted = checkDeleted;
    }

    public static NoticeCommentDto convertCommentToDto(NoticeComment noticeComment) {
        return new NoticeCommentDto(noticeComment.getNo(), noticeComment.getMember(),
                noticeComment.getDetail(), noticeComment.getWriteDate(), noticeComment.getNotice(),
                noticeComment.getParentComment(), noticeComment.getCheckDeleted());
    }
}