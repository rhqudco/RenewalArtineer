package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class NoticeComment {
    @Id @GeneratedValue
    private Long no;

    private Long noticeNo;
    private Long parentCommentNo;
    private String writer;
    private LocalDateTime writeDate;
    private String generation;
    private String detail;
}