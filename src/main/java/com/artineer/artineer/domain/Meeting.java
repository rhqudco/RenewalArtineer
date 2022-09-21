package com.artineer.artineer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Meeting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meeting_no")
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private LocalDateTime writeDate;
    private String title;
    private String detail;
    private String fileName;
    private String imageName;
    private Long view;

    @OneToMany(mappedBy = "meeting")
    private List<MeetingComment> comments = new ArrayList<>();
}
