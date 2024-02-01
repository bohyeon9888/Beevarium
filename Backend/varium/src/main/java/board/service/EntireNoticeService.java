package com.pjt.beevarium.board.service;

import com.pjt.beevarium.board.entity.EntireNotice;
import com.pjt.beevarium.board.repository.EntireNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntireNoticeService {

    private final EntireNoticeRepository entireNoticeRepository;

    @Autowired
    public EntireNoticeService(EntireNoticeRepository entireNoticeRepository) {
        this.entireNoticeRepository = entireNoticeRepository;
    }

    public List<EntireNotice> findAllEntireNotices() {
        return entireNoticeRepository.findAll();
    }

    public EntireNotice findEntireNoticeById(Integer id) {
        return entireNoticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("EntireNotice not found with id: " + id));
    }

    public EntireNotice saveEntireNotice(EntireNotice entireNotice) {
        return entireNoticeRepository.save(entireNotice);
    }

    public void deleteEntireNotice(Integer id) {
        // 데이터베이스에서 주어진 ID에 해당하는 EntireNotice 엔티티를 삭제합니다.
        entireNoticeRepository.deleteById(id);
    }
    // 추가적인 비즈니스 로직이 필요한 경우 여기에 구현
}
