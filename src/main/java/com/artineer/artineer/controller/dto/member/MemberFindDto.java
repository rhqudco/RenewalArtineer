package com.artineer.artineer.controller.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class MemberFindDto {
    private String id;
    private String name;
    private String emailId;
    private String emailDomain;
}