package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByNo(Long no);
    Page<Notice> findAll(Pageable pageable);
    void deleteByNo(Long no);
}