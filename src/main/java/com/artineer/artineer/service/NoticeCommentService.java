package com.artineer.artineer.service;

import com.artineer.artineer.domain.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    void saveNoticeComment(NoticeComment noticeComment);
    void deleteNoticeComment(Long no);
    List<NoticeComment> findAllComment();
    Long findByNo(Long no);
}