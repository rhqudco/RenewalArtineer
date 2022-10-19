package com.artineer.artineer.controller.dto.notice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubNoticeCommentDto {
    private Long parentNo;
    private String detail;
}