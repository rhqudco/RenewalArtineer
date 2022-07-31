package com.artineer.artineer.service;

import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.repository.NoticeCommentRepository;
import com.artineer.artineer.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentRepository noticeCommentRepository;

    @Override
    @Transactional
    public void saveNoticeComment(NoticeComment noticeComment) {
        noticeCommentRepository.save(noticeComment);
    }

    @Override
    @Transactional
    public void deleteNoticeComment(Long no) {
        noticeCommentRepository.remove(no);
    }
}
