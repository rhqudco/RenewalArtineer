package com.artineer.artineer.controller.dto;

import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSaveForm {
    private String id;
    private String password;
    private String name;
    private Birth birth;
    private String email;
    private Phone phone;
    private String gender;
    private String generation;
    private String level;
}