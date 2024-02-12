package b203.varium.board.contoller;

import b203.varium.board.dto.BroadcastStationNoticeDto;
import b203.varium.board.dto.StationNoticeDTO;
import b203.varium.board.dto.UpdateNoticeDTO;
import b203.varium.board.service.BroadcastStationNoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/notice/broadcasting")
@RequiredArgsConstructor
public class BroadcastStationNoticeController {

    private final BroadcastStationNoticeService broadcastStationNoticeService;

    // 개인 방송국 공지 조회
    @GetMapping("/{broadcast_station_no}")
    public ResponseEntity<Map<String, Object>> getBroadcastStationNotices(@PathVariable("broadcast_station_no") Integer broadcastStationNo) {
        log.info("broadcastStationNo = {}", broadcastStationNo);
        List<StationNoticeDTO> notices = broadcastStationNoticeService.findNoticesByStationId(broadcastStationNo);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", notices);
        return ResponseEntity.ok(response);
    }

    // 방송국 공지사항 게시글 삽입
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createBroadcastStationNotice(
            @Validated @RequestBody BroadcastStationNoticeDto broadcastStationNoticeDto, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", result.getAllErrors().toString());
            return ResponseEntity.badRequest().body(errorResponse);
        }

        broadcastStationNoticeService.saveBroadcastStationNotice(broadcastStationNoticeDto);
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("message", "Broadcast station notice created successfully.");
        return ResponseEntity.ok(successResponse);
    }

    // 방송국 공지사항 게시글 조회
    @GetMapping("/board/{broadcasting_station_notice_no}")
    public ResponseEntity<Map<String, Object>> getBroadcastStationNotice(@PathVariable("broadcasting_station_notice_no") Integer noticeNo) {
        StationNoticeDTO notice = broadcastStationNoticeService.findBroadcastStationNoticeById(noticeNo);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", notice);
        return ResponseEntity.ok(response);
    }

    // 방송국 공지사항 수정
    @PutMapping("/board/update")
    public ResponseEntity<Map<String, String>> updateBroadcastStationNotice(
            @Validated @RequestBody UpdateNoticeDTO updateNoticeDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", result.getAllErrors().toString());
            return ResponseEntity.badRequest().body(errorResponse);
        }

        broadcastStationNoticeService.updateBroadcastStationNotice(updateNoticeDTO);
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("message", "Broadcast station notice updated successfully.");
        return ResponseEntity.ok(successResponse);
    }

    // 방송국 공지사항 삭제
    @DeleteMapping("/board/delete/{broadcasting_station_notice_no}")
    public ResponseEntity<Map<String, String>> deleteBroadcastStationNotice(@PathVariable("broadcasting_station_notice_no") Integer noticeNo) {
        broadcastStationNoticeService.deleteBroadcastStationNotice(noticeNo);
        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("message", "Broadcast station notice deleted successfully.");
        return ResponseEntity.ok(successResponse);
    }
}
