package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class NoticeComment {
    @Id
    @GeneratedValue
    @Column(name = "noticeComment_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private String detail;
    private LocalDateTime writeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_no")
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentComment_no")
    private NoticeComment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.PERSIST)
    private List<NoticeComment> childComments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private checkDeleted checkDeleted;

    protected NoticeComment() {
    }

    private void setParentComment(NoticeComment parentComment) {
        this.parentComment = parentComment;
    }


    // 연관관계 편의 메소드
    public void addChildComment(NoticeComment childComment, NoticeComment parent) {
        childComment.setParentComment(parent);
        parent.childComments.add(childComment);
    }

    private NoticeComment(Member member, String detail, LocalDateTime writeDate, Notice notice, checkDeleted checkDeleted) {
        this.member = member;
        this.detail = detail;
        this.writeDate = writeDate;
        this.notice = notice;
        this.checkDeleted = checkDeleted;
    }

    public static NoticeComment writeComment(Member member, String detail, LocalDateTime writeDate, Notice notice, checkDeleted checkDeleted) {
        if (notice == null) {
            throw new IllegalStateException("글이 없습니다.");
        }
        NoticeComment noticeComment = new NoticeComment(member, detail, writeDate, notice, checkDeleted);
        return noticeComment;
    }

    public static NoticeComment writeChildComment(Member member, String detail, LocalDateTime writeDate, Notice notice, NoticeComment parentComment, checkDeleted checkDeleted) {
        if (notice == null) {
            throw new IllegalStateException("글이 없습니다.");
        }
        NoticeComment childComment = new NoticeComment(member, detail, writeDate, notice, checkDeleted);
        childComment.addChildComment(childComment, parentComment);

        return childComment;
    }
}