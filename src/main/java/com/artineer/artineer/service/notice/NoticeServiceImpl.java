package com.artineer.artineer.service.notice;

import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.repository.notice.NoticeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService{

    private final NoticeJpaRepository noticeJpaRepository;

    @Override
    @Transactional
    public Notice saveNotice(Notice notice) {
        return noticeJpaRepository.save(notice);
    }
}