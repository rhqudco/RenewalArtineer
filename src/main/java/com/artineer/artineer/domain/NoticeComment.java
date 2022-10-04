package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class NoticeComment {
    @Id
    @GeneratedValue
    @Column(name = "noticeComment_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String detail;
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_no")
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentComment_no")
    private NoticeComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<NoticeComment> childComments = new ArrayList<>();

    protected NoticeComment() {
    }

    protected void setNotice(Notice notice) {
        if (notice != null) {
            this.notice = notice;
        }
    }
    private NoticeComment(Member member, String detail, LocalDateTime writeDate) {
        this.member = member;
        this.detail = detail;
        this.writeDate = writeDate;
    }

    public static NoticeComment writeComment(Member member, String detail, LocalDateTime writeDate, Notice notice) {
        NoticeComment noticeComment = new NoticeComment(member, detail, writeDate);
        if (notice != null) {
            noticeComment.setNotice(notice);
        }
        return noticeComment;
    }
}