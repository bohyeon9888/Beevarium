package b203.varium.broadcastStation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
public class BroadstationController {

    // 내 방송국 조회
    @GetMapping("/view/{stationId}")
    public void viewMyStation(@PathVariable int stationId) {
        
    }

    // 방송국 내 금지 멤버 조회


    // 방송국 내 후원 내역 조회(후순위 기능)

}
