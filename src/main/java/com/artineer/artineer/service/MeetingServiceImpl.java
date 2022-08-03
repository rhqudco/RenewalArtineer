package com.artineer.artineer.service;

import com.artineer.artineer.domain.Meeting;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    @Override
    public void saveMeeting(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    @Override
    public List<Meeting> findAllMeeting() {
        return meetingRepository.findAll();
    }

    @Override
    public void deleteMeeting(Long no) {
        meetingRepository.remove(no);
    }

    @Override
    public List<Meeting> findByTitle(String title) {
        return meetingRepository.findByTitle(title);
    }
}
