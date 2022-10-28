package com.artineer.artineer.service.noticeComment;

import com.artineer.artineer.controller.dto.noticeComment.NoticeCommentDto;
import com.artineer.artineer.domain.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    List<NoticeCommentDto> findAllCommentOfNotice(Long no);
    List<NoticeComment> findByNo(Long noticeCommentNo);
    NoticeComment save(NoticeComment noticeComment);
    List<NoticeComment> lookUpComment(Long noticeNo);
    void deleteCommentHaveChild(Long no);
    void deleteComment(Long no);
    void deleteAllCommentByNotice(Long noticeNo);
}