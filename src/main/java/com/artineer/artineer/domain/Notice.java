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
    @Id @GeneratedValue
    @Column(name = "notice_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private LocalDateTime writeDate;
    private String title;
    private String detail;
    private String fileName;
    private String imageName;

    @OneToMany(mappedBy = "notice")
    private List<NoticeComment> comments = new ArrayList<>();
}
