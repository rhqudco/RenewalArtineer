package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByNo(Long no);
}