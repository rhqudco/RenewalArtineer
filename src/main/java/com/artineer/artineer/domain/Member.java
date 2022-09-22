package com.artineer.artineer.domain;

import com.artineer.artineer.controller.dto.MemberSaveForm;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Member")
@Getter @Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    @Embedded
    private Birth birth;
    @NotBlank
    private String email;
    @NotBlank
    @Embedded
    private Phone phone;
    @NotBlank
    private String gender;
    @NotBlank
    private String generation;
    @NotBlank
    private String level;

    protected Member() {
    }

    public Member(String id, String password, String name, Birth birth, String email, Phone phone, String gender, String generation, String level) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.generation = generation;
        this.level = level;
    }

    public Member(MemberSaveForm dto) {
        this.id = dto.getId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.birth = dto.getBirth();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.gender = dto.getGender();
        this.generation = dto.getGeneration();
        this.level = dto.getLevel();
    }
}