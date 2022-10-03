package com.artineer.artineer.service.notice;

import com.artineer.artineer.domain.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    List<NoticeComment> findAllCommentOfNotice(Long no);
}