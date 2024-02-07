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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        BroadcastStationNotice notice = new BroadcastStationNotice();

        // BroadcastStation 엔티티를 찾아옵니다.
        BroadcastStation broadcastStation = broadcastStationRepository.findById(broadcastStationNoticeDto.getBroadcastStationNo())
                .orElseThrow(() -> new RuntimeException("BroadcastStation not found with id: " + broadcastStationNoticeDto.getBroadcastStationNo()));

        // BroadcastStation 엔티티를 BroadcastStationNotice에 설정합니다.
        notice.setBroadcastStation(broadcastStation);
        notice.setBroadcastStationNoticeTitle(broadcastStationNoticeDto.getBroadcastStationNoticeTitle());
        notice.setBroadcastStationNoticeContent(broadcastStationNoticeDto.getBroadcastStationNoticeContent());

        notice.setCreatedDate(nowT);
        notice.setUpdatedDate(nowT);

        broadcastStationNoticeRepository.save(notice);
    }

    public Optional<BroadcastStationNotice> getBroadcastStationNoticeById(Integer id) {
        return broadcastStationNoticeRepository.findById(id);
    }

    public List<BroadcastStationNotice> getAllBroadcastStationNotices() {
        return broadcastStationNoticeRepository.findAll();
    }

    @Transactional
    public void deleteBroadcastStationNotice(Integer id) {
        broadcastStationNoticeRepository.deleteById(id);
    }

    public BroadcastStationNoticeDto findBroadcastStationNoticeById(Integer noticeNo) {
        BroadcastStationNotice broadcastStationNotice = broadcastStationNoticeRepository.findById(noticeNo)
                .orElseThrow(() -> new RuntimeException("BroadcastStationNotice not found with id: " + noticeNo));

        return new BroadcastStationNoticeDto(
                broadcastStationNotice.getBroadcastStationNoticeNo(),
                broadcastStationNotice.getBroadcastStation().getId(),
                broadcastStationNotice.getBroadcastStationNoticeTitle(),
                broadcastStationNotice.getBroadcastStationNoticeContent(),
                broadcastStationNotice.getCreatedDate(),
                broadcastStationNotice.getUpdatedDate());
    }

    public List<BroadcastStationNoticeDto> findNoticesByStationId(Integer broadcastStationNo) {
        // 이 메소드의 구현은 BroadcastStation과 BroadcastStationNotice 엔티티 간의 관계에 따라 다릅니다.
        // 예시: return broadcastStationNoticeRepository.findByBroadcastStationId(broadcastStationNo);
        Set<BroadcastStationNotice> notices = broadcastStationNoticeCustomRepository.findNoticesByStationId(broadcastStationNo);
        List<BroadcastStationNoticeDto> broadcastStationNoticeDtos = new ArrayList<>();
        for (BroadcastStationNotice notice : notices) {
            BroadcastStationNoticeDto broadcastStationNoticeDto = new BroadcastStationNoticeDto(
                    notice.getBroadcastStationNoticeNo(),
                    notice.getBroadcastStation().getId(),
                    notice.getBroadcastStationNoticeTitle(),
                    notice.getBroadcastStationNoticeContent(),
                    notice.getCreatedDate(),
                    notice.getUpdatedDate());

            broadcastStationNoticeDtos.add(broadcastStationNoticeDto);
        }
        return broadcastStationNoticeDtos;
    }

    // 수정
    @Transactional
    public void updateBroadcastStationNotice(BroadcastStationNoticeDto broadcastStationNoticeDto) {
        log.info("broadcastStationNoticeDto={}", broadcastStationNoticeDto);
        //optional null 값 체크
        BroadcastStationNotice broadcastStationNotice = broadcastStationNoticeRepository.findById(broadcastStationNoticeDto.getBroadcastStationNoticeNo())
                .orElseThrow(() -> new RuntimeException("BroadcastStation not found with id: " + broadcastStationNoticeDto.getBroadcastStationNo()));
        log.info("broadcastStationNotice = {}", broadcastStationNotice);

        broadcastStationNotice.setBroadcastStationNoticeTitle(broadcastStationNoticeDto.getBroadcastStationNoticeTitle());
        broadcastStationNotice.setBroadcastStationNoticeContent(broadcastStationNoticeDto.getBroadcastStationNoticeContent());

        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        broadcastStationNotice.setUpdatedDate(nowT);
    }


    // 필요한 경우, BroadcastStationNoticeCustomRepository의 메소드를 사용하여 추가적인 비즈니스 로직 구현
}
