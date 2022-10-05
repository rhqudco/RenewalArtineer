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


    // 연관관계 편의 메소드
    public void addChildComment(NoticeComment noticeComment, NoticeComment parent) {
        childComments.add(noticeComment);
        parentComment.setParentComment(parent);
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

    public static NoticeComment writeChildComment(Member member, String detail, LocalDateTime writeDate, Notice notice, NoticeComment parentComment) {
        NoticeComment childComment = new NoticeComment(member, detail, writeDate);
        if (notice != null) {
            childComment.setNotice(notice);
        }
        childComment.addChildComment(childComment, parentComment);

        return childComment;
    }
}