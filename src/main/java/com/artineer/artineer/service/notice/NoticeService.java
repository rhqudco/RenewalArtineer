package com.artineer.artineer.service.notice;

import com.artineer.artineer.controller.dto.notice.NoticeCondition;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    Notice saveNotice(Notice notice);
    List<Notice> lookUpNotice(Long no);
    Page<Notice> findAllNotice(Pageable pageable);
    void updateNoticeView(Long no);
    Page<Notice> findNoticeTitleOrId(Pageable pageable, NoticeCondition condition);
 }