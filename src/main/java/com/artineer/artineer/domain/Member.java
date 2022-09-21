package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Table(name = "Member")
@Getter @Setter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Long no;

    private String id;
    private String password;
    private String name;
    @Embedded
    private Birth birth;
    private String email;
    @Embedded
    private Phone phone;
    private String gender;
    private String generation;
    private String level;

    protected Member() {
    }

    public Member(long no, String id, String password, String name, Birth birth, String email, Phone phone, String gender, String generation, String level) {
        this.no = no;
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

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
}