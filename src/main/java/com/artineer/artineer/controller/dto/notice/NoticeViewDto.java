package com.artineer.artineer.controller.dto.notice;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.UploadFile;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeViewDto {
    private Long no;
    private String writer;
    private LocalDateTime writeDate;
    private String title;
    private String detail;
    private UploadFile uploadFile;
    private Long view;

    public NoticeViewDto(Long no, String writer, LocalDateTime writeDate, String title, String detail, UploadFile uploadFile, Long view) {
        this.no = no;
        this.writer = writer;
        this.writeDate = writeDate;
        this.title = title;
        this.detail = detail;
        this.uploadFile = uploadFile;
        this.view = view;
    }

    public static NoticeViewDto convertNoticeDto(Notice notice) {
        return new NoticeViewDto(notice.getNo(), notice.getMember().getId(), notice.getWriteDate(), notice.getTitle(), notice.getDetail(), notice.getUploadFile(), notice.getView());
    }
}