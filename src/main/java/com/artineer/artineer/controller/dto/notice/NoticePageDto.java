package com.artineer.artineer.controller.dto.notice;

import com.artineer.artineer.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class NoticePageDto {
    private Long no;
    private String writer;
    private LocalDateTime writeDate;
    private String title;
    private Long view;
    private String generation;

    private NoticePageDto(Long no, String writer, LocalDateTime writeDate, String title, Long view, String generation) {
        this.no = no;
        this.writer = writer;
        this.writeDate = writeDate;
        this.title = title;
        this.view = view;
        this.generation = generation;
    }

    public static NoticePageDto createNoticePageDto(Long no, String writer, LocalDateTime writeDate, String title, Long view, String generation) {
        return new NoticePageDto(no, writer, writeDate, title, view, generation);
    }
}