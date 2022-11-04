package com.artineer.artineer;

import com.artineer.artineer.common.WebSecurityConfig;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberService memberService;
    private final WebSecurityConfig webSecurityConfig;
    private final NoticeService noticeService;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        Member member = new Member("admin", webSecurityConfig.getPasswordEncoder().encode("1"),
                "고병채", "rhqudco1204@naver.com");
        memberService.join(member);
        Member member2 = new Member("admin2", webSecurityConfig.getPasswordEncoder().encode("1"),
                "고병채", "rhqudco1204@naver.com");
        memberService.join(member2);

        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "title" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "admin" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "test" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "example" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "dance" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "singer" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "project" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "spring" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "thymeleaf" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "coding" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "monologue" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "abin" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "jae" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "bch" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "family" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "wish" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "love" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "please" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }
        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "god" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }        for (int i = 1; i <= 40; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "cometrue" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }



    }
}