package b203.varium.board.service;

import b203.varium.board.dto.EntireNoticeDto;
import b203.varium.board.entity.EntireNotice;
import b203.varium.board.repository.EntireNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntireNoticeService {

    private final EntireNoticeRepository entireNoticeRepository;

    // 저장
    @Transactional
    public Map<String, Object> saveEntireNotice(EntireNoticeDto entireNoticeDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("entireNoticeDto={}", entireNoticeDto);
            Timestamp nowT = new Timestamp(System.currentTimeMillis());
            EntireNotice notice = new EntireNotice();

//            notice.setEntireNoticeNo(entireNoticeDto.getEntireNoticeNo());
            notice.setEntireNoticeTitle(entireNoticeDto.getEntireNoticeTitle());
            notice.setEntireNoticeContent(entireNoticeDto.getEntireNoticeContent());

            notice.setCreatedDate(nowT);
            notice.setUpdatedDate(nowT);

            entireNoticeRepository.save(notice);

            response.put("status", "success");
            response.put("data", "EntireNotice saved successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }

    public List<EntireNotice> findAllEntireNotices() {
        return entireNoticeRepository.findAll();
    }

    public Map<String, Object> findEntireNoticeById(Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            EntireNotice entireNotice = entireNoticeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("EntireNotice not found with id: " + id));

            response.put("status", "success");
            response.put("data", new EntireNoticeDto(
                    entireNotice.getId(),
                    entireNotice.getEntireNoticeTitle(),
                    entireNotice.getEntireNoticeContent(),
                    entireNotice.getCreatedDate(),
                    entireNotice.getUpdatedDate()));
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }

    // 수정
    @Transactional
    public Map<String, Object> updateEntireNotice(EntireNoticeDto entireNoticeDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            log.info("entireNoticeDto={}", entireNoticeDto);
            //optional null 값 체크
            EntireNotice entireNotice = entireNoticeRepository.findById(entireNoticeDto.getEntireNoticeNo())
                    .orElseThrow(() -> new RuntimeException("BroadcastStation not found with id: " + entireNoticeDto.getEntireNoticeNo()));
            log.info("entireNotice = {}", entireNotice);

            entireNotice.setEntireNoticeTitle(entireNoticeDto.getEntireNoticeTitle());
            entireNotice.setEntireNoticeContent(entireNoticeDto.getEntireNoticeContent());

            Timestamp nowT = new Timestamp(System.currentTimeMillis());

            entireNotice.setUpdatedDate(nowT);

            response.put("status", "success");
            response.put("data", "EntireNotice updated successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("error", e.getMessage());
        }

        return response;
    }

    @Transactional
    public Map<String, Object> deleteEntireNotice(int id) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> msg = new HashMap<>();

        EntireNotice noti = entireNoticeRepository.findById(id);
        log.info(String.valueOf(noti.getId()));
        entireNoticeRepository.delete(noti);

        if (entireNoticeRepository.existsById(id)) {
            response.put("status", "fail");
            msg.put("msg", "make error");
            response.put("data", msg);

        } else {
            response.put("status", "success");
            response.put("data", "EntireNotice deleted successfully");

        }
        return response;
    }
    // 추가적인 비즈니스 로직이 필요한 경우 여기에 구현
}
