package b203.varium.board.service;

import b203.varium.board.dto.EntireNoticeDto;
import b203.varium.board.entity.EntireNotice;
import b203.varium.board.repository.EntireNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntireNoticeService {

    private final EntireNoticeRepository entireNoticeRepository;


    // 저장
    @Transactional
    public void saveEntireNotice(EntireNoticeDto entireNoticeDto) {
        log.info("entireNoticeDto={}", entireNoticeDto);
        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        EntireNotice notice = new EntireNotice();

//        notice.setEntireNoticeNo(entireNoticeDto.getEntireNoticeNo());
        notice.setEntireNoticeTitle(entireNoticeDto.getEntireNoticeTitle());
        notice.setEntireNoticeContent(entireNoticeDto.getEntireNoticeContent());

        notice.setCreatedDate(nowT);
        notice.setUpdatedDate(nowT);

        entireNoticeRepository.save(notice);
    }

//    @Transactional
//    public void saveEntireStationNotice(EntireNoticeDto entireNoticeDto) {
//        log.info("entireNoticeDto={}", entireNoticeDto);
//        Timestamp nowT = new Timestamp(System.currentTimeMillis());
//        EntireNotice notice = new EntireNotice();
//
//
//        notice.setEntireNoticeTitle(entireNoticeDto.getEntireNoticeTitle());
//        notice.setEntireNoticeContent(entireNoticeDto.getEntireNoticeContent());
//
//        notice.setCreatedDate(nowT);
//        notice.setUpdatedDate(nowT);
//
//        entireNoticeRepository.save(notice);

//    }


    public List<EntireNotice> findAllEntireNotices() {
        return entireNoticeRepository.findAll();
    }

    public EntireNoticeDto findEntireNoticeById(Integer id) {
        EntireNotice entireNotice = entireNoticeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EntireNotice not found with id: " + id));

        return new EntireNoticeDto(
                entireNotice.getEntireNoticeNo(),
                entireNotice.getEntireNoticeTitle(),
                entireNotice.getEntireNoticeContent(),
                entireNotice.getCreatedDate(),
                entireNotice.getUpdatedDate());
    }

//    public EntireNotice saveEntireNotice(EntireNotice entireNotice) {
//        return entireNoticeRepository.save(entireNotice);
//    }

    // 수정
    @Transactional
    public void updateEntireNotice(EntireNoticeDto entireNoticeDto) {
        log.info("entireNoticeDto={}", entireNoticeDto);
        //optional null 값 체크
        EntireNotice entireNotice = entireNoticeRepository.findById(entireNoticeDto.getEntireNoticeNo())
                .orElseThrow(() -> new RuntimeException("BroadcastStation not found with id: " + entireNoticeDto.getEntireNoticeNo()));
        log.info("entireNotice = {}", entireNotice);

        entireNotice.setEntireNoticeTitle(entireNoticeDto.getEntireNoticeTitle());
        entireNotice.setEntireNoticeContent(entireNoticeDto.getEntireNoticeContent());

        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        entireNotice.setUpdatedDate(nowT);
    }


    public void deleteEntireNotice(Integer id) {
        // 데이터베이스에서 주어진 ID에 해당하는 EntireNotice 엔티티를 삭제합니다.
        entireNoticeRepository.deleteById(id);
    }
    // 추가적인 비즈니스 로직이 필요한 경우 여기에 구현
}
