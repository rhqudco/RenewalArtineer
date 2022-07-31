package com.artineer.artineer.repository;

import com.artineer.artineer.domain.NoticeComment;

public interface NoticeCommentRepository {
    // 작성, 수정
    void save(NoticeComment noticeComment);
    void remove(Long no);
}
