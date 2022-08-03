package com.artineer.artineer.service;

import com.artineer.artineer.domain.Meeting;
import java.util.List;

public interface MeetingService {
    void saveMeeting(Meeting meeting);
    List<Meeting> findAllMeeting();
    void deleteMeeting(Long no);
    List<Meeting> findByTitle(String title);
}
