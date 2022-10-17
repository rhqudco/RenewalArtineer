package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private LocalDateTime writeDate;
    private String title;
    private String detail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadFile_no")
    private UploadFile uploadFile;

    private Long view;

    protected Notice() {
    }

    public Notice(Member member, LocalDateTime writeDate, String title, String detail, Long view) {
        this.member = member;
        this.writeDate = writeDate;
        this.title = title;
        this.detail = detail;
        this.view = view;
    }

    // 연관관계 편의 메소드
    public void addUploadFile(UploadFile uploadFile) {
        if (uploadFile != null) {
            this.uploadFile = uploadFile;
            uploadFile.addNotice(this);
        }
    }

    public static Notice writeNotice(Member member, LocalDateTime writeDate, String title,
                                     String detail, UploadFile uploadFile, Long view) {
        Notice notice = new Notice(member, writeDate, title, detail, view);
        if (uploadFile != null) {
            notice.addUploadFile(uploadFile);
        }
        return notice;
    }

    public void updateNoticeView() {
        this.view++;
    }
}