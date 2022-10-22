package com.artineer.artineer.controller.dto.noticeComment;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.NoticeComment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeCommentDto {
    private Member member;
    private String detail;
    private LocalDateTime writeDate;
    private Notice notice;
    private NoticeComment parentComment;
    private List<NoticeCommentDto> childComments = new ArrayList<>();

    public NoticeCommentDto(Member member, String detail, LocalDateTime writeDate, Notice notice, NoticeComment parentComment) {
        this.member = member;
        this.detail = detail;
        this.writeDate = writeDate;
        this.notice = notice;
        this.parentComment = parentComment;
    }

    public static NoticeCommentDto convertCommentToDto(NoticeComment noticeComment) {
        return new NoticeCommentDto(noticeComment.getMember(),
                noticeComment.getDetail(), noticeComment.getWriteDate(), noticeComment.getNotice(),
                noticeComment.getParentComment());
    }
}