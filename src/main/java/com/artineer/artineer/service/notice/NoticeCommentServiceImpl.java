package com.artineer.artineer.service.notice;

import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.repository.notice.NoticeCommentJpaRepository;
import com.artineer.artineer.repository.notice.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentJpaRepository noticeCommentJpaRepository;

    @Override
    public List<NoticeComment> findAllCommentOfNotice(Long no) {
        return noticeCommentJpaRepository.findByNoticeNo(no);
    }
}