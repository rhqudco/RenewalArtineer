package com.artineer.artineer.service;

import com.artineer.artineer.domain.NoticeComment;

public interface NoticeCommentService {
    void saveNoticeComment(NoticeComment noticeComment);
    void deleteNoticeComment(Long no);
}
