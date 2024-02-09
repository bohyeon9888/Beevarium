package b203.varium.broadcastStation.controller;

import b203.varium.broadcastStation.dto.MyStationReqDTO;
import b203.varium.broadcastStation.service.BroadcastStationService;
import com.amazonaws.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/station")
public class BroadstationController {

    private final BroadcastStationService broadcastStationService;

    // 내 방송국 조회
    @GetMapping("/view/{streamerId}")
    public ResponseEntity<Map<String, Object>> viewMyStation(@PathVariable String streamerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        return ResponseEntity.ok(broadcastStationService.viewMyStation(streamerId, name));
    }

    // 방송국 내 금지 멤버 조회


    // 방송국 내 후원 내역 조회(후순위 기능)

}
