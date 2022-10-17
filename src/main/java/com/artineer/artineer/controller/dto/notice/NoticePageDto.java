package com.artineer.artineer.controller.dto.notice;

import com.artineer.artineer.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticePageDto {
    private Long no;
    private String writer;
    private LocalDateTime writeDate;
    private String detail;
    private Long view;

    private NoticePageDto(Long no, String writer, LocalDateTime writeDate, String detail, Long view) {
        this.no = no;
        this.writer = writer;
        this.writeDate = writeDate;
        this.detail = detail;
        this.view = view;
    }

    public static NoticePageDto createNoticePageDto(Long no, String writer, LocalDateTime writeDate, String detail, Long view) {
        return new NoticePageDto(no, writer, writeDate, detail, view);
    }
}