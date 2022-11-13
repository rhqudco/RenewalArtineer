package com.artineer.artineer.controller.dto.member;

import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.AllArgsConstructor;
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
    private String emailId;
    @NotBlank
    private String emailDomain;
    @Embedded
    private Phone phone;
    @NotBlank
    private String gender;
    @NotBlank
    private String generation;

    /*
    * emailId + @ + emailDomain = email
    * */
    private String email;

    public MemberModifyDto() {
    }

    public MemberModifyDto(String id, String password, String name, Birth birth, String emailId, String emailDomain, Phone phone, String gender, String generation) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.emailId = emailId;
        this.emailDomain = emailDomain;
        this.phone = phone;
        this.gender = gender;
        this.generation = generation;
    }

    public MemberModifyDto(String id, String password, String name, Birth birth, String email, Phone phone, String gender, String generation) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.generation = generation;
    }

    public MemberModifyDto(String id, String name, Birth birth, String emailId, String emailDomain, Phone phone, String gender, String generation) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.emailId = emailId;
        this.emailDomain = emailDomain;
        this.phone = phone;
        this.gender = gender;
        this.generation = generation;
    }

    public static MemberModifyDto modifyMemberDto(String id, String password, String name, Birth birth, String email, Phone phone, String gender, String generation) {
        return new MemberModifyDto(id, password, name, birth, email, phone, gender, generation);
    }

    public static MemberModifyDto modifyFormDto(String id, String name, Birth birth, String emailId, String emailDomain, Phone phone, String gender, String generation) {
        return new MemberModifyDto(id, name, birth, emailId, emailDomain, phone, gender, generation);
    }
}