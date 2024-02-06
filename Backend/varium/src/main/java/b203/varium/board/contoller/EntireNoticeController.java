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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice/entire")
@RequiredArgsConstructor
public class EntireNoticeController {

    private final EntireNoticeService entireNoticeService;

    // 전체 공지 조회
    @GetMapping
    public ResponseEntity<List<EntireNotice>> getAllEntireNotices() {
        List<EntireNotice> notices = entireNoticeService.findAllEntireNotices();
        return ResponseEntity.ok().body(notices);
    }

    // 전체 공지사항 게시글 삽입
    @PostMapping
    public ResponseEntity<String> createEntireNotice(
            @Validated
            @RequestBody EntireNoticeDto entireNoticeDto, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.ok().body(result.getNestedPath());

        entireNoticeService.saveEntireNotice(entireNoticeDto);
        return ResponseEntity.ok().body("success");
    }

    // 전체 공지사항 게시글 조회
    @GetMapping("/{no}")
    public ResponseEntity<EntireNoticeDto> getEntireNotice(@PathVariable("no") Integer id) {
        EntireNoticeDto notice = entireNoticeService.findEntireNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }

    // 전체 공지사항 수정
    @PutMapping
    public ResponseEntity<String> updateEntireNotice(
            @Validated
            @RequestBody EntireNoticeDto entireNoticeDto, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.ok().body(result.getNestedPath());
        log.info("entireNoticeDto= {}", entireNoticeDto);
        entireNoticeService.updateEntireNotice(entireNoticeDto);
        return ResponseEntity.ok().body("success");
    }

    // 전체 공지사항 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<String> deleteEntireNotice(@PathVariable("no") Integer id) {
        entireNoticeService.deleteEntireNotice(id);
        return ResponseEntity.ok().body("success");
    }
}
