package b203.varium.broadcasting.controller;

import b203.varium.broadcasting.dto.ListRespDTO;
import b203.varium.broadcasting.dto.ReqDTO;
import b203.varium.broadcasting.service.BroadcastingService;
import b203.varium.chatting.dto.SaveReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
public class BroadcastingController {

    private final BroadcastingService broadcastingService;

    @PostMapping("/start")
    public ResponseEntity<Map<String, Object>> createBroadcasting(@RequestBody ReqDTO reqDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(broadcastingService.startBroadcasting(auth.getName(),
                reqDTO.getBroadcastingTitle(), reqDTO.getTagList()));
    }

    // 사용자가 구독중인 스트리머 실시간 방송 목록
    @GetMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> viewSubscriberList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("user name : " + auth.getName());

        return ResponseEntity.ok(broadcastingService.subscribeListBroadcasting(auth.getName()));
    }

    // 실시간 방송 조회
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> viewBroadcastingList() {
        return ResponseEntity.ok(broadcastingService.getBroadcasting());
    }


    @PostMapping("/end")
    public ResponseEntity<Map<String, Object>> deleteBroadcasting(@RequestBody SaveReqDTO saveReqDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(broadcastingService.endBroadcasting(auth.getName(), saveReqDTO.getChatting()));
    }

    // 생방송 화면 들어갈 때 팔로우 여부, 스트리머정보
    @GetMapping("/enter/{streamerId}")
    public ResponseEntity<Map<String, Object>> enterBroadcasting(@PathVariable String streamerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(broadcastingService.enterBroadcasting(auth.getName(), streamerId));
    }
}
