package com.artineer.artineer.controller.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberLoginDto {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
}