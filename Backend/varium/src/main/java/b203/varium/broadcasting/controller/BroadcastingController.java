package b203.varium.broadcasting.controller;

import b203.varium.broadcasting.dto.ReqDTO;
import b203.varium.broadcasting.service.BroadcastingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/live")
public class BroadcastingController {

    private final BroadcastingService broadcastingService;

    public BroadcastingController(BroadcastingService broadcastingService) {
        this.broadcastingService = broadcastingService;
    }

    @PostMapping("/start")
    public void createBroadcasting(ReqDTO reqDTO) {

    }
}
