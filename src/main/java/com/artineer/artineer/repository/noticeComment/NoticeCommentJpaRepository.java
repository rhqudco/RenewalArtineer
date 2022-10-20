package com.artineer.artineer.repository.noticeComment;

import com.artineer.artineer.domain.NoticeComment;

import java.util.List;

public interface NoticeCommentJpaRepository {
    List<NoticeComment> findByNoticeNo(Long noticeNo);
    NoticeComment save(NoticeComment noticeComment);
}