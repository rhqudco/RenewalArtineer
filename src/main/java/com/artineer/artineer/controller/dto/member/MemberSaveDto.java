package com.artineer.artineer.controller.dto.member;

import com.artineer.artineer.domain.Level;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberSaveDto {
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Embedded
    
    private Birth birth;

    @NotBlank
    private String emailId;

    @NotBlank
    private String emailDomain;

    @Embedded
    private Phone phone;

    @NotBlank
    private String gender;

    @NotBlank
    private String generation;

    private Level level;
}