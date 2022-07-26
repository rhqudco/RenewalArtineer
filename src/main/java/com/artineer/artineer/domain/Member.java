package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private long no;

    private String id;
    private String password;
    private String name;
    private String year;
    private String month;
    private String day;
    private String email;
    private String phone;
    private String gender;
    private String generation;
    private String level;
}
