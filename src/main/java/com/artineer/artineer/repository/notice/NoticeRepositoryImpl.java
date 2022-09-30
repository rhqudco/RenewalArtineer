package com.artineer.artineer.repository.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl {

    private final EntityManager em;

}