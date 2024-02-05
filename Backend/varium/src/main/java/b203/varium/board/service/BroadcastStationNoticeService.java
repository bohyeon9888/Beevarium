// BroadcastStationNoticeService.java
package b203.varium.board.service;

import b203.varium.board.dto.BroadcastStationNoticeDto;
import b203.varium.board.entity.BroadcastStationNotice;
import b203.varium.board.repository.BroadcastStationNoticeCustomRepository;
import b203.varium.board.repository.BroadcastStationNoticeRepository;
import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BroadcastStationNoticeService {

    // BroadcastStationRepository를 주입받아야 합니다.
    private final BroadcastStationRepository broadcastStationRepository;
    private final BroadcastStationNoticeRepository broadcastStationNoticeRepository;
    private final BroadcastStationNoticeCustomRepository broadcastStationNoticeCustomRepository;

    @Transactional
    public void saveBroadcastStationNotice(BroadcastStationNoticeDto broadcastStationNoticeDto) {
        log.info("broadcastStationNoticeDto={}", broadcastStationNoticeDto);
        BroadcastStationNotice notice = new BroadcastStationNotice();

        // BroadcastStation 엔티티를 찾아옵니다.
        BroadcastStation broadcastStation = broadcastStationRepository.findById(broadcastStationNoticeDto.getBroadcastStationNo())
                .orElseThrow(() -> new RuntimeException("BroadcastStation not found with id: " + broadcastStationNoticeDto.getBroadcastStationNo()));

        // BroadcastStation 엔티티를 BroadcastStationNotice에 설정합니다.
        notice.setBroadcastStation(broadcastStation);
        notice.setBroadcastStationNoticeTitle(broadcastStationNoticeDto.getTitle());
        notice.setBroadcastStationNoticeContent(broadcastStationNoticeDto.getContent());

        broadcastStationNoticeRepository.save(notice);
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
        return broadcastStationNoticeCustomRepository.findNoticesByStationId(broadcastStationNo); // 실제 구현 필요
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
