package com.artineer.artineer.controller.dto.member;

import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberModifyDto {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @Embedded
    private Birth birth;
    @NotBlank
    private String email;
    @Embedded
    private Phone phone;
    @NotBlank
    private String gender;
    @NotBlank
    private String generation;

    public MemberModifyDto(String id, String name, Birth birth, String email, Phone phone, String gender, String generation) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.generation = generation;
    }

    public static MemberModifyDto modifyMember(String id, String name, Birth birth, String email, Phone phone, String gender, String generation) {
        return new MemberModifyDto(id, name, birth, email, phone, gender, generation);
    }
}