package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploadFile_no")
    private UploadFile fileName;
    private Long view;

    @OneToMany(mappedBy = "notice")
    private List<NoticeComment> comments = new ArrayList<>();

    protected Notice() {
    }

    public Notice(Member member, LocalDateTime writeDate, String title, String detail, UploadFile fileName, Long view) {
        this.member = member;
        this.writeDate = writeDate;
        this.title = title;
        this.detail = detail;
        this.view = view;
    }

    public static Notice writeNotice(Member member, LocalDateTime writeDate, String title,
                                     String detail, UploadFile fileName, Long view) {
        return new Notice(member, writeDate, title, detail, fileName, view);
    }
}