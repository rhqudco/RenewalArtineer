package com.artineer.artineer.service;

import com.artineer.artineer.domain.Member;
import com.artineer.artineer.domain.Notice;
import com.artineer.artineer.domain.NoticeComment;
import com.artineer.artineer.service.member.MemberService;
import com.artineer.artineer.service.notice.NoticeService;
import com.artineer.artineer.service.noticeComment.NoticeCommentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class NoticeCommentServiceImplTest {

    @Autowired
    MemberService memberService;
    @Autowired
    NoticeCommentService noticeCommentService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    EntityManager em;

    @Test
    void writeNoticeCommentTest() {
        Member findMember = memberService.findMember(1L).get(0);

        Notice writeNotice = Notice.writeNotice(findMember, LocalDateTime.now(), "title", "detail", null, 0L);
        noticeService.saveNotice(writeNotice);

        NoticeComment comment = NoticeComment.writeComment(findMember, "detail", LocalDateTime.now(), writeNotice);

        // 저장된 객체
        NoticeComment savedNoticeComment = noticeCommentService.save(comment);
        Long savedNoticeCommentNo = savedNoticeComment.getNo();

//        em.flush();
//        em.clear();

        // 저장된 객체를 불러온 객체
        NoticeComment comment1 = noticeCommentService.lookUpComment(savedNoticeCommentNo).get(0);

        // em.flush(), em.clear()를 하지 않으면 같은 객체다.(트랜잭션이 끝나지 않았고, 영속성 컨텍스트에서 가져오기 때문.)
        // em.flush(), em.clear()를 하면 comment1은 조회를 통해 가져오기 때문에 다른 객체다.
        System.out.println("savedNoticeComment = " + savedNoticeComment);
        System.out.println("comment1 = " + comment1);

        // 저장된 객체와, 불러온 객체의 기본키를 비교.
        assertThat(savedNoticeComment.getNo()).isEqualTo(comment1.getNo());
        assertThat(savedNoticeComment).isEqualTo(comment1);

        em.flush();
        em.clear();

        NoticeComment comment2 = noticeCommentService.lookUpComment(savedNoticeCommentNo).get(0);

        System.out.println("comment2 = " + comment2);

        assertThat(savedNoticeComment).isNotEqualTo(comment2);
    }

    @Test
    void writeChildCommentTest() {
        Member findMember = memberService.findMember(1L).get(0);

        Notice writeNotice = Notice.writeNotice(findMember, LocalDateTime.now(), "title", "detail", null, 0L);
        noticeService.saveNotice(writeNotice);

        NoticeComment comment = NoticeComment.writeComment(findMember, "detail", LocalDateTime.now(), writeNotice);
        noticeCommentService.save(comment);


        em.flush();
        em.clear();

        System.out.println("==========================================================");

        NoticeComment childComment = NoticeComment.writeChildComment(findMember, "childDetail", LocalDateTime.now(), writeNotice, comment);
        noticeCommentService.save(childComment);

        em.flush();
        em.clear();

        System.out.println("==========================================================");

        NoticeComment childComment2 = NoticeComment.writeChildComment(findMember, "childDetail2", LocalDateTime.now(), writeNotice, comment);
        noticeCommentService.save(childComment2);

        em.flush();
        em.clear();

        System.out.println("==========================================================");

        List<NoticeComment> allCommentOfNotice = noticeCommentService.findAllCommentOfNotice(writeNotice.getNo());

        for (NoticeComment noticeComment : allCommentOfNotice) {
            System.out.println("=======================시작===================================");
            System.out.println(noticeComment);
            System.out.println("noticeComment.getDetail() = " + noticeComment.getDetail());
            if (!noticeComment.getChildComments().isEmpty()) {
                List<NoticeComment> childComments = noticeComment.getChildComments();
                List<String> collect = childComments.stream()
                        .map(NoticeComment::getDetail)
                        .filter(detail -> !detail.isEmpty())
                        .collect(toList());
                System.out.println("==========================================================");
                System.out.println("collect = " + collect);
                System.out.println("==========================================================");
            }
            System.out.println("===========================끝===============================");
        }
    }
}