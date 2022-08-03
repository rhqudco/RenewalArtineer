package com.artineer.artineer.repository;

import com.artineer.artineer.domain.Meeting;
import java.util.List;

public interface MeetingRepository {
    // 전체 글 조회
    List<Meeting> findAll();
    // 글 작성 & 수정
    void save(Meeting meeting);

    List<Meeting> findByTitle(String title);
    void remove(Long no);

}
