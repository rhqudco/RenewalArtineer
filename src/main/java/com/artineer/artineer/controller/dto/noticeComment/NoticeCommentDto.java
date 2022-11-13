package com.artineer.artineer.controller.dto.noticeComment;

import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.domain.CheckDeleted;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoticeCommentDto {
    private Long no;
    private String writer;
    private String generation;
    private String detail;
    private LocalDateTime writeDate;
    private Notice notice;
    private NoticeComment parentComment;
    private List<NoticeCommentDto> childComments = new ArrayList<>();
    private CheckDeleted checkDeleted;

    public NoticeCommentDto(Long no, String writer, String generation, String detail, LocalDateTime writeDate, Notice notice, NoticeComment parentComment, CheckDeleted checkDeleted) {
        this.no = no;
        this.writer = writer;
        this.generation = generation;
        this.detail = detail;
        this.writeDate = writeDate;
        this.notice = notice;
        this.parentComment = parentComment;
        this.checkDeleted = checkDeleted;
    }

    public static NoticeCommentDto convertCommentToDto(NoticeComment noticeComment) {
        return new NoticeCommentDto(noticeComment.getNo(), noticeComment.getMember().getName(),
                noticeComment.getMember().getGeneration(), noticeComment.getDetail(), noticeComment.getWriteDate(),
                noticeComment.getNotice(), noticeComment.getParentComment(), noticeComment.getCheckDeleted());
    }
}