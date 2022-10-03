package com.artineer.artineer.repository.notice;

import com.artineer.artineer.domain.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {
}
