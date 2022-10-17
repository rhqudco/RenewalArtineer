package com.artineer.artineer.controller.dto.notice;

import com.artineer.artineer.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticePageDto {
    private Long no;
    private String writer;
    private LocalDateTime writeDate;
    private String title;
    private Long view;

    private NoticePageDto(Long no, String writer, LocalDateTime writeDate, String title, Long view) {
        this.no = no;
        this.writer = writer;
        this.writeDate = writeDate;
        this.title = title;
        this.view = view;
    }

    public static NoticePageDto createNoticePageDto(Long no, String writer, LocalDateTime writeDate, String title, Long view) {
        return new NoticePageDto(no, writer, writeDate, title, view);
    }
}