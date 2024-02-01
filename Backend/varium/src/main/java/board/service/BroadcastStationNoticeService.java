// BroadcastStationNoticeService.java
package com.pjt.beevarium.board.service;

import com.pjt.beevarium.board.entity.BroadcastStationNotice;
import com.pjt.beevarium.board.repository.BroadcastStationNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BroadcastStationNoticeService {

    private final BroadcastStationNoticeRepository broadcastStationNoticeRepository;

    @Autowired
    public BroadcastStationNoticeService(BroadcastStationNoticeRepository broadcastStationNoticeRepository) {
        this.broadcastStationNoticeRepository = broadcastStationNoticeRepository;
    }

    public BroadcastStationNotice saveBroadcastStationNotice(Integer stationNo, BroadcastStationNotice notice) {
        return broadcastStationNoticeRepository.save(notice);
    }

    public Optional<BroadcastStationNotice> getBroadcastStationNoticeById(Integer id) {
        return broadcastStationNoticeRepository.findById(id);
    }

    public List<BroadcastStationNotice> getAllBroadcastStationNotices() {
        return broadcastStationNoticeRepository.findAll();
    }

    public void deleteBroadcastStationNotice(Integer id) {
        broadcastStationNoticeRepository.deleteById(id);
    }

    public BroadcastStationNotice findBroadcastStationNoticeById(Integer noticeNo) {
        return broadcastStationNoticeRepository.findById(noticeNo)
                .orElseThrow(() -> new RuntimeException("BroadcastStationNotice not found with id: " + noticeNo));
    }

    public List<BroadcastStationNotice> findNoticesByStationId(Integer broadcastStationNo) {
        // 이 메소드의 구현은 BroadcastStation과 BroadcastStationNotice 엔티티 간의 관계에 따라 다릅니다.
        // 예시: return broadcastStationNoticeRepository.findByBroadcastStationId(broadcastStationNo);
        return null; // 실제 구현 필요
    }

    public BroadcastStationNotice updateBroadcastStationNotice(Integer noticeNo, BroadcastStationNotice broadcastStationNotice) {
        BroadcastStationNotice existingNotice = findBroadcastStationNoticeById(noticeNo);
        // 여기에서 existingNotice의 속성을 broadcastStationNotice의 속성으로 업데이트합니다.
        // 예시: existingNotice.setTitle(broadcastStationNotice.getTitle());
        // 예시: existingNotice.setContent(broadcastStationNotice.getContent());
        // 실제 구현 필요

        return broadcastStationNoticeRepository.save(existingNotice);
    }

    // 필요한 경우, BroadcastStationNoticeCustomRepository의 메소드를 사용하여 추가적인 비즈니스 로직 구현
}
