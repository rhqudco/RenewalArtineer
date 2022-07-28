package com.artineer.artineer.service;

import com.artineer.artineer.domain.Notice;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class NoticeServiceImplTest {
    @Autowired
    NoticeService noticeService;

    @Test
    @DisplayName("글 작성 테스트")
    void writeNoticeTest() {

    }

}