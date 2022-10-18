package com.artineer.artineer.domain;

import com.artineer.artineer.controller.dto.member.MemberModifyDto;
import com.artineer.artineer.controller.dto.member.MemberSaveDto;
import com.artineer.artineer.domain.embeddable.Birth;
import com.artineer.artineer.domain.embeddable.Phone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Member")
@Getter
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

    @Enumerated(EnumType.STRING)
    private Level level;

    protected Member() {
    }

    // 테스트용 데이터 위한 생성자
    public Member(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public Member(String id, String password, String name, Birth birth, String email, Phone phone, String gender, String generation, Level level) {
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

    public Member(MemberSaveDto dto) {
        this.id = dto.getId();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.birth = dto.getBirth();
        this.email = dto.getEmailId();
        this.phone = dto.getPhone();
        this.gender = dto.getGender();
        this.generation = dto.getGeneration();
//        this.level = dto.getLevel();
    }

    public static Member createMember(String id, String password, String name, Birth birth, String email, Phone phone, String gender, String generation, Level level) {
        return new Member(id, password, name, birth, email, phone, gender, generation, level);
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void modifyMember(MemberModifyDto memberModifyDto) {
        this.id = memberModifyDto.getId();
        this.password = memberModifyDto.getPassword();
        this.name = memberModifyDto.getName();
        this.email = memberModifyDto.getEmail();
        this.birth = memberModifyDto.getBirth();
        this.phone = memberModifyDto.getPhone();
        this.gender = memberModifyDto.getGender();
        this.generation = memberModifyDto.getGeneration();
    }
}