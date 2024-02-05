package b203.varium.board.contoller;

import b203.varium.board.service.EntireNoticeService;
import b203.varium.board.entity.EntireNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice/entire")
public class EntireNoticeController {

    private final EntireNoticeService entireNoticeService;

    @Autowired
    public EntireNoticeController(EntireNoticeService entireNoticeService) {
        this.entireNoticeService = entireNoticeService;
    }

    // 전체 공지 조회
    @GetMapping
    public ResponseEntity<List<EntireNotice>> getAllEntireNotices() {
        List<EntireNotice> notices = entireNoticeService.findAllEntireNotices();
        return ResponseEntity.ok().body(notices);
    }

    // 전체공지 게시글 삽입
    @PostMapping
    public ResponseEntity<EntireNotice> addEntireNotice(@RequestBody EntireNotice entireNotice) {
        EntireNotice savedNotice = entireNoticeService.saveEntireNotice(entireNotice);
        return ResponseEntity.ok().body(savedNotice);
    }

    // 전체 공지사항 게시글 조회
    @GetMapping("/{no}")
    public ResponseEntity<EntireNotice> getEntireNoticeById(@PathVariable("no") Integer id) {
        EntireNotice notice = entireNoticeService.findEntireNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }

    // 전체 공지사항 수정
    @PutMapping("/{no}")
    public ResponseEntity<EntireNotice> updateEntireNotice(@PathVariable("no") Integer id, @RequestBody EntireNotice entireNotice) {
        entireNotice.setEntireNoticeNo(id); // URL의 ID를 엔티티에 설정
        EntireNotice updatedNotice = entireNoticeService.saveEntireNotice(entireNotice);
        return ResponseEntity.ok().body(updatedNotice);
    }

    // 전체 공지사항 삭제
    @DeleteMapping("/{no}")
    public ResponseEntity<Void> deleteEntireNotice(@PathVariable("no") Integer id) {
        entireNoticeService.deleteEntireNotice(id);
        return ResponseEntity.ok().build();
    }
}
