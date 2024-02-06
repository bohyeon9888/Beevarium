package b203.varium.board.contoller;

import b203.varium.board.dto.EntireNoticeDto;
import b203.varium.board.entity.EntireNotice;
import b203.varium.board.service.EntireNoticeService;
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
@RestController
@RequestMapping("/notice/entire")
@RequiredArgsConstructor
public class EntireNoticeController {

    private final EntireNoticeService entireNoticeService;

    // 전체 공지 조회
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEntireNotices() {
        List<EntireNotice> notices = entireNoticeService.findAllEntireNotices();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", notices);
        return ResponseEntity.ok().body(response);
    }

    // 전체 공지사항 게시글 삽입
    @PostMapping
    public ResponseEntity<Map<String, Object>> createEntireNotice(
            @Validated @RequestBody EntireNoticeDto entireNoticeDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("error", result.getNestedPath());
            return ResponseEntity.ok().body(response);
        }

        response = entireNoticeService.saveEntireNotice(entireNoticeDto);
        return ResponseEntity.ok().body(response);
    }

    // 전체 공지사항 게시글 조회
    @GetMapping("/{no}")
    public ResponseEntity<Map<String, Object>> getEntireNotice(@PathVariable("no") int id) {
        Map<String, Object> response = entireNoticeService.findEntireNoticeById(id);
        return ResponseEntity.ok().body(response);
    }

    // 전체 공지사항 수정
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateEntireNotice(
            @Validated @RequestBody EntireNoticeDto entireNoticeDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("status", "error");
            response.put("error", result.getNestedPath());
            return ResponseEntity.ok().body(response);
        }

        log.info("entireNoticeDto= {}", entireNoticeDto);
        response = entireNoticeService.updateEntireNotice(entireNoticeDto);
        return ResponseEntity.ok().body(response);
    }

    // 전체 공지사항 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Map<String, Object>> deleteEntireNotice(@PathVariable("no") int id) {
//        System.out.println(id);

        Map<String, Object> response = entireNoticeService.deleteEntireNotice(id);
        return ResponseEntity.ok().body(response);
    }
}
