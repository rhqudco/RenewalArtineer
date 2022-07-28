package com.artineer.artineer.service;

import com.artineer.artineer.domain.Notice;

import java.util.List;

public interface NoticeService {
    void saveNotice(Notice notice);
    List<Notice> findAllNotice();
    void deleteNotice(Long no);
    List<Notice> findByTitle(String title);
}