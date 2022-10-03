package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.NoticeComment;

import java.util.List;

public interface NoticeCommentJpaRepository {
    List<NoticeComment> findByNoticeNo(Long no);
}