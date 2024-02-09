package b203.varium.broadcasting.controller;

import b203.varium.broadcasting.dto.ListRespDTO;
import b203.varium.broadcasting.dto.ReqDTO;
import b203.varium.broadcasting.service.BroadcastingService;
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
                reqDTO.getBroadcastingTitle(), reqDTO.getBroadcastingImgUrl(), reqDTO.getTagList()));
    }

    // 사용자가 구독중인 스트리머 실시간 방송 목록
    @GetMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> viewBroadcastingList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("user name : " + auth.getName());

        return ResponseEntity.ok(broadcastingService.subscribeListBroadcasting(auth.getName()));
    }

    // 랜덤 실시간 방송 5개 조회


    // 시청자 높은 순 방송 5개 조회


    @GetMapping("/end")
    public ResponseEntity<Map<String, String>> deleteBroadcasting() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(broadcastingService.endBroadcasting(auth.getName()));
    }

    // 생방송 화면 들어갈 때 팔로우 여부, 스트리머정보
    @GetMapping("/enter/{streamerId}")
    public ResponseEntity<Map<String, Object>> enterBroadcasting(@PathVariable String streamerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(broadcastingService.enterBroadcasting(auth.getName(), streamerId));
    }
}
