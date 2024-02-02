package b203.varium.board.contoller;

import b203.varium.board.entity.BroadcastStationNotice;
import b203.varium.board.service.BroadcastStationNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice/broadcasting")
public class BroadcastStationNoticeController {

    private final BroadcastStationNoticeService broadcastStationNoticeService;

    @Autowired
    public BroadcastStationNoticeController(BroadcastStationNoticeService broadcastStationNoticeService) {
        this.broadcastStationNoticeService = broadcastStationNoticeService;
    }

    // 개인 방송국 공지 조회
    @GetMapping
    public ResponseEntity<List<BroadcastStationNotice>> getBroadcastStationNotices(@RequestParam("broadcast_station_no") Integer broadcastStationNo) {
        List<BroadcastStationNotice> notices = broadcastStationNoticeService.findNoticesByStationId(broadcastStationNo);
        return ResponseEntity.ok().body(notices);
    }

    // 방송국 공지사항 게시글 삽입
    @PostMapping("/{broadcasting_station_no}")
    public ResponseEntity<BroadcastStationNotice> createBroadcastStationNotice(@PathVariable("broadcasting_station_no") Integer stationNo,
                                                                               @RequestBody BroadcastStationNotice broadcastStationNotice) {
        BroadcastStationNotice savedNotice = broadcastStationNoticeService.saveBroadcastStationNotice(stationNo, broadcastStationNotice);
        return ResponseEntity.ok().body(savedNotice);
    }

    // 방송국 공지사항 게시글 조회
    @GetMapping("/{broadcasting_station_notice_no}")
    public ResponseEntity<BroadcastStationNotice> getBroadcastStationNotice(@PathVariable("broadcasting_station_notice_no") Integer noticeNo) {
        BroadcastStationNotice notice = broadcastStationNoticeService.findBroadcastStationNoticeById(noticeNo);
        return ResponseEntity.ok().body(notice);
    }

    // 방송국 공지사항 수정
    @PutMapping("/{broadcasting_station_notice_no}")
    public ResponseEntity<BroadcastStationNotice> updateBroadcastStationNotice(
            @PathVariable("broadcasting_station_notice_no") Integer noticeNo,
            @RequestBody BroadcastStationNotice broadcastStationNotice) {

        BroadcastStationNotice updatedNotice = broadcastStationNoticeService.updateBroadcastStationNotice(noticeNo, broadcastStationNotice);
        return ResponseEntity.ok().body(updatedNotice);
    }

    // 방송국 공지사항 삭제
    @DeleteMapping("/{broadcasting_station_notice_no}")
    public ResponseEntity<Void> deleteBroadcastStationNotice(@PathVariable("broadcasting_station_notice_no") Integer broadcastStationNoticeNo) {
        broadcastStationNoticeService.deleteBroadcastStationNotice(broadcastStationNoticeNo);
        return ResponseEntity.ok().build();
    }
}
