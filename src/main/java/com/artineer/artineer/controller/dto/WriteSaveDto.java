package com.artineer.artineer.controller.dto;

import com.artineer.artineer.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class WriteSaveDto {
    private Member member;
    private LocalDateTime writeDate;
    @NotBlank
    private String title;
    @NotBlank
    private String detail;
    private String fileName;
    private String imageName;
    private Long view;
}