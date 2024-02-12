// BroadcastStationNoticeService.java
package b203.varium.board.service;

import b203.varium.board.dto.BroadcastStationNoticeDto;
import b203.varium.board.dto.UpdateNoticeDTO;
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
        BroadcastStation broadcastStation = broadcastStationRepository.findById(broadcastStationNoticeDto.getBroadcastStationNo());

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
                broadcastStationNotice.getBroadcastStation().getId(),
                broadcastStationNotice.getBroadcastStationNoticeTitle(),
                broadcastStationNotice.getBroadcastStationNoticeContent());
    }

    public List<BroadcastStationNoticeDto> findNoticesByStationId(Integer broadcastStationNo) {
        List<BroadcastStationNotice> notices = broadcastStationNoticeCustomRepository.findNoticesByStationId(broadcastStationNo);
        List<BroadcastStationNoticeDto> broadcastStationNoticeDtos = new ArrayList<>();
        for (BroadcastStationNotice notice : notices) {
            broadcastStationNoticeDtos.add(new BroadcastStationNoticeDto(
                    notice.getBroadcastStation().getId(),
                    notice.getBroadcastStationNoticeTitle(),
                    notice.getBroadcastStationNoticeContent()));
        }
        return broadcastStationNoticeDtos;
    }


    // 수정
    @Transactional
    public void updateBroadcastStationNotice(UpdateNoticeDTO updateNoticeDTO) {
        log.info("broadcastStationNoticeDto={}", updateNoticeDTO);
        //optional null 값 체크
        BroadcastStationNotice broadcastStationNotice = broadcastStationNoticeRepository.findById(updateNoticeDTO.getBroadcastStationNo())
                .orElseThrow(() -> new RuntimeException("BroadcastStation not found with id: " + updateNoticeDTO.getBroadcastStationNo()));
        log.info("broadcastStationNotice = {}", broadcastStationNotice);

        broadcastStationNotice.setBroadcastStationNoticeTitle(updateNoticeDTO.getBroadcastStationNoticeTitle());
        broadcastStationNotice.setBroadcastStationNoticeContent(updateNoticeDTO.getBroadcastStationNoticeContent());

        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        broadcastStationNotice.setUpdatedDate(nowT);
        broadcastStationNoticeRepository.save(broadcastStationNotice);
    }


    // 필요한 경우, BroadcastStationNoticeCustomRepository의 메소드를 사용하여 추가적인 비즈니스 로직 구현
}
