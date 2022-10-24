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

        for (int i = 1; i <= 100; i++) {
            Notice notice = Notice.writeNotice(member, LocalDateTime.now(), "title" + i, "detail", null, 0L);
            noticeService.saveNotice(notice);
        }

    }
}