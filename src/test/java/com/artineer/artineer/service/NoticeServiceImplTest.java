package com.artineer.artineer.service;

import com.artineer.artineer.controller.dto.notice.NoticeCondition;
import com.artineer.artineer.controller.dto.notice.NoticePageDto;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.service.notice.NoticeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@SpringBootTest
class NoticeServiceImplTest {

    @Autowired
    NoticeService noticeService;

    @Test
    void findNoticeDynamicQuery() {
        NoticeCondition condition = new NoticeCondition();
        condition.setTitle("title");
        PageRequest page = PageRequest.of(0, 15);

        Page<NoticePageDto> noticeTitleOrId = noticeService.findNoticeTitleOrId(page, condition);

        for (NoticePageDto noticePageDto : noticeTitleOrId) {
            System.out.println("noticePageDto = " + noticePageDto);
        }
    }

}