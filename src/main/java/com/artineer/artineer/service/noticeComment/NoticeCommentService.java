package com.artineer.artineer.service.noticeComment;

import com.artineer.artineer.domain.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    List<NoticeComment> findAllCommentOfNotice(Long no);
    NoticeComment save(NoticeComment noticeComment);
    List<NoticeComment> lookUpComment(Long no);
}