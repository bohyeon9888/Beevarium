package b203.varium.azure.controller;

import b203.varium.azure.service.AzureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AzureController {

    private final AzureService azureService;

    @MessageMapping("/change")
    public ResponseEntity<String> processText(@RequestBody String text) {
        log.info("text = {}", text);
        azureService.addTextToProcess(text);
        return new ResponseEntity<>("Running", HttpStatus.ACCEPTED);
    }
}
