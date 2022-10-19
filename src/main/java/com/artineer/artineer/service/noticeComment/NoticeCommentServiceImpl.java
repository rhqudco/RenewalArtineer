package com.artineer.artineer.service.noticeComment;

import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.repository.noticeComment.NoticeCommentJpaRepository;
import com.artineer.artineer.repository.noticeComment.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentJpaRepository noticeCommentJpaRepository;
    private final NoticeCommentRepository noticeCommentRepository;

    @Override
    @Transactional
    public NoticeComment save(NoticeComment noticeComment) {
        return noticeCommentJpaRepository.save(noticeComment);
    }

    @Override
    public List<NoticeComment> findAllCommentOfNotice(Long no) {
        return noticeCommentJpaRepository.findByNoticeNo(no);
    }

    @Override
    public List<NoticeComment> lookUpComment(Long no) {
        return noticeCommentRepository.findByNo(no);
    }
}