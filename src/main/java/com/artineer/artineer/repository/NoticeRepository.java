package com.artineer.artineer.repository;

import com.artineer.artineer.domain.Notice;

import java.util.List;

public interface NoticeRepository {
    // 전체 글 조회
    List<Notice> findAll();
    // 글 작성 & 수정
    void save(Notice notice);

    List<Notice> findByTitle(String title);
    void remove(Long no);
}