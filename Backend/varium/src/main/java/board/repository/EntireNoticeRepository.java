// Spring Data JPA 사용
package com.pjt.beevarium.board.repository;

import com.pjt.beevarium.board.entity.BroadcastStationNotice;
import com.pjt.beevarium.board.entity.EntireNotice;
import com.pjt.beevarium.broadcastStation.entity.BroadcastStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface EntireNoticeRepository extends JpaRepository<EntireNotice, Integer> {
        List<EntireNotice> findByEntireNoticeTitleContaining(String title);
        List<EntireNotice> findByEntireNoticeContentContaining(String content);
    }
