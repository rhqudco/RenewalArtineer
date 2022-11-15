package com.artineer.artineer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Meeting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    @JsonIgnore
    private Member member;

    private LocalDateTime writeDate;
    private String title;
    private String detail;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadFile_no")
    private UploadFile uploadFile;

    private Long view;

    protected Meeting() {
    }

    public Meeting(Member member, LocalDateTime writeDate, String title, String detail, Long view) {
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
            uploadFile.addMeeting(this);
        }
    }

    public static Meeting writeMeeting(Member member, LocalDateTime writeDate, String title,
                                      String detail, UploadFile uploadFile, Long view) {
        Meeting meeting = new Meeting(member, writeDate, title, detail, view);
        if (uploadFile != null) {
            meeting.addUploadFile(uploadFile);
        }
        return meeting;
    }

    public void updateNoticeView() {
        this.view++;
    }
}