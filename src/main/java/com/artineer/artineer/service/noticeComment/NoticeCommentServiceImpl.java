package com.artineer.artineer.service.noticeComment;

import com.artineer.artineer.controller.dto.noticeComment.NoticeCommentDto;
import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.repository.noticeComment.NoticeCommentJpaRepository;
import com.artineer.artineer.repository.noticeComment.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.artineer.artineer.controller.dto.noticeComment.NoticeCommentDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentJpaRepository noticeCommentJpaRepository;
    private final NoticeCommentRepository noticeCommentRepository;

    @Override
    @Transactional
    public NoticeComment save(NoticeComment noticeComment) {
        return noticeCommentJpaRepository.save(noticeComment);
    }

    @Override
    public List<NoticeCommentDto> findAllCommentOfNotice(Long noticeNo) {
        return convertCommentStructure(noticeCommentJpaRepository.findByNoticeNo(noticeNo));
    }

    @Override
    public List<NoticeComment> lookUpComment(Long no) {
        return noticeCommentRepository.findByNo(no);
    }

    private List<NoticeCommentDto> convertCommentStructure(List<NoticeComment> noticeComments) {
        List<NoticeCommentDto> result = new ArrayList<>();
        Map<Long, NoticeCommentDto> map = new HashMap<>();

        noticeComments.stream().forEach(nc -> {
            NoticeCommentDto dto = convertCommentToDto(nc);
            map.put(dto.getNo(), dto);

            if (nc.getParentComment() != null) {
                /*
                NoticeCommentDto parent = map.get(dto.getParentComment().getNo());
                int idx = result.indexOf(parent);
                result.get(idx).getChildComments().add(dto);
                */
                map.get(nc.getParentComment().getNo()).getChildComments().add(dto);
            } else {
                result.add(dto);
            }
        });
        return result;
    }

    @Override
    @Transactional
    public void deleteCommentHaveChild(Long no) {
        NoticeComment.deleteCommentHaveChild(noticeCommentRepository.findByNo(no).get(0));
    }

    @Override
    public List<NoticeComment> findByNo(Long noticeCommentNo) {
        return noticeCommentRepository.findByNo(noticeCommentNo);
    }

    @Override
    @Transactional
    public void deleteComment(Long no) {
        noticeCommentRepository.deleteByNo(no);
    }

    @Override
    @Transactional
    public void deleteAllCommentByNotice(Long noticeNo) {
        noticeCommentRepository.deleteAllByNoticeNo(noticeNo);
    }
}