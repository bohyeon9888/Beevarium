package b203.varium.board.contoller;

import b203.varium.board.dto.BroadcastStationNoticeDto;
import b203.varium.board.service.BroadcastStationNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice/broadcasting")
@RequiredArgsConstructor
public class BroadcastStationNoticeController {

    private final BroadcastStationNoticeService broadcastStationNoticeService;

    // 개인 방송국 공지 조회
    @GetMapping
    public ResponseEntity<List<BroadcastStationNoticeDto>> getBroadcastStationNotices(@RequestParam("broadcast_station_no") Integer broadcastStationNo) {
        log.info("broadcastStationNo = {}", broadcastStationNo);
        return ResponseEntity.ok().body(broadcastStationNoticeService.findNoticesByStationId(broadcastStationNo));
    }

    // 방송국 공지사항 게시글 삽입
    @PostMapping
    public ResponseEntity<String> createBroadcastStationNotice(
            @Validated
            @RequestBody BroadcastStationNoticeDto broadcastStationNoticeDto, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.ok().body(result.getNestedPath());

        broadcastStationNoticeService.saveBroadcastStationNotice(broadcastStationNoticeDto);
        return ResponseEntity.ok().body("success");
    }

    // 방송국 공지사항 게시글 조회
    @GetMapping("/{broadcasting_station_notice_no} ")
    public ResponseEntity<BroadcastStationNoticeDto> getBroadcastStationNotice(@PathVariable("broadcasting_station_notice_no") Integer noticeNo) {
        BroadcastStationNoticeDto notice = broadcastStationNoticeService.findBroadcastStationNoticeById(noticeNo);
        return ResponseEntity.ok().body(notice);
    }

    // 방송국 공지사항 수정
    @PutMapping
    public ResponseEntity<String> updateBroadcastStationNotice(
            @Validated
            @RequestBody BroadcastStationNoticeDto broadcastStationNoticeDto, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.ok().body(result.getNestedPath());
        log.info("broadcastStationNoticeDto= {}", broadcastStationNoticeDto);
        broadcastStationNoticeService.updateBroadcastStationNotice(broadcastStationNoticeDto);
        return ResponseEntity.ok().body("success");
    }

    // 방송국 공지사항 삭제
    @DeleteMapping("/{broadcasting_station_notice_no}")
    public ResponseEntity<String> deleteBroadcastStationNotice(@PathVariable("broadcasting_station_notice_no") Integer noticeNo) {

        broadcastStationNoticeService.deleteBroadcastStationNotice(noticeNo);
        return ResponseEntity.ok().body("success");
    }
}
