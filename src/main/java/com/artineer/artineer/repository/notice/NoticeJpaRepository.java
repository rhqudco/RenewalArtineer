package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.Notice;

import java.util.List;

public interface NoticeJpaRepository {
    // 글 작성 & 수정
    Notice save(Notice notice);
}