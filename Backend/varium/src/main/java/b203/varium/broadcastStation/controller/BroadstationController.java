package b203.varium.broadcastStation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
public class BroadstationController {

    @GetMapping("/my")
    public void viewMyStation() {

    }

}
