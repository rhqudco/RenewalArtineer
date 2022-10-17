package com.artineer.artineer.service.notice;

import com.artineer.artineer.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
    Notice saveNotice(Notice notice);
    List<Notice> lookUpNotice(Long no);
    Page<Notice> findAllNotice(Pageable pageable);
    void updateNoticeView(Long no);
 }