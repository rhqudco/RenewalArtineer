package com.artineer.artineer.service.notice;

import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public void saveNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    @Override
    public List<Notice> findAllNotice() {
        return noticeRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteNotice(Long no) {
        noticeRepository.remove(no);
    }

    @Override
    public List<Notice> findByTitle(String title) {
        return noticeRepository.findByTitle(title);
    }
}