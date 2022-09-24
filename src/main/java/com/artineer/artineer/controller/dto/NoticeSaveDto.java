package com.artineer.artineer.controller.dto;

import com.artineer.artineer.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeSaveDto {
    private Member member;
    private LocalDateTime writeDate;
    private String title;
    private String detail;
    private String fileName;
    private String imageName;
    private Long view;
}