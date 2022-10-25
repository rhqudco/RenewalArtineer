package com.artineer.artineer.repository.notice;

import com.artineer.artineer.controller.dto.notice.NoticeCondition;
import com.artineer.artineer.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeJpaRepository {
    Page<Notice> searchNoticePage(NoticeCondition condition, Pageable pageable);
}