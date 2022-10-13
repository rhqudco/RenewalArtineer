package com.artineer.artineer.controller.dto.notice;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.UploadFile;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeFindDto {
    private Member member;
    private LocalDateTime writeDate;
    private String title;
    private String detail;
    private UploadFile uploadFile;
    private Long view;
}