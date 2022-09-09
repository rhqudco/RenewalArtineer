package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Meeting {
    @Id @GeneratedValue
    @Column(name = "meeting_no")
    private Long no;

    private String writer;
    private LocalDateTime writeDate;
    private String generation;
    private String title;
    private String detail;
    private String fileName;
    private String imageName;
}
