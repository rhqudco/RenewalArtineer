package com.artineer.artineer.service.notice;

import com.artineer.artineer.controller.dto.notice.NoticeCondition;
import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.repository.notice.NoticeJpaRepository;
import com.artineer.artineer.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;
    private final NoticeJpaRepository noticeJpaRepository;

    @Override
    @Transactional
    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public List<Notice> lookUpNotice(Long no) {
        return noticeRepository.findByNo(no);
    }

    @Override
    public Page<Notice> findAllNotice(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateNoticeView(Long no) {
        Notice findNotice = noticeRepository.findByNo(no).get(0);
        findNotice.updateNoticeView();
    }

    @Override
    public Page<Notice> findNoticeTitleOrId(Pageable pageable, NoticeCondition condition) {
        return noticeJpaRepository.searchNoticePage(condition, pageable);
    }
}