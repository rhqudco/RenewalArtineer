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
//        List<NoticeCommentDto> result = new ArrayList<>();
//        Map<Long, NoticeCommentDto> map = new HashMap<>();
//        noticeComments.stream().forEach(nc -> {
//            NoticeCommentDto dto = convertCommentToDto(nc);
//            map.put(dto.getNo(), dto);
//            if (nc.getParentComment() != null) {
//                map.get(nc.getParentComment().getNo()).getChildComments().add(dto);
//            } else {
//                result.add(dto);
//            }
//        });
//        return result;
        List<NoticeCommentDto> result = new ArrayList<>();
        List<NoticeCommentDto> result2 = new ArrayList<>();
        Map<Long, NoticeCommentDto> map = new HashMap<>();
        List<NoticeCommentDto> temp = new ArrayList<>();

        noticeComments.stream().forEach(nc -> {
            NoticeCommentDto dto = convertCommentToDto(nc);
            result.add(dto);
        });

        List<NoticeComment> collect = noticeComments
                .stream()
                .filter(nc -> nc.getParentComment() != null)
                .collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            temp.add(result.get(i));
            for (int j = 0; j < collect.size(); j++) {
                if (collect.get(j) != null) {
                    if (result.get(i).getNo().equals(collect.get(j).getParentComment().getNo())) {
                        temp.add(j+1, NoticeCommentDto.convertCommentToDto(collect.get(j)));
                    }
                } else {
                    temp.add(result.get(j));
                }
            }
        }
        return temp;
    }
}