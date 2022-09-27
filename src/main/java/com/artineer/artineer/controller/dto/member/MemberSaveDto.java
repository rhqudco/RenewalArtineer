package com.artineer.artineer.controller.dto.member;

import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberSaveDto {
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private Birth birth;

    @NotBlank
    private String emailId;

    @NotBlank
    private String emailDomain;

    private Phone phone;

    @NotBlank
    private String gender;

    @NotBlank
    private String generation;

    private String level;
}