package b203.varium.azure.controller;

import b203.varium.azure.service.AzureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AzureController {

    private final AzureService azureService;

    @MessageMapping("/change")
    public ResponseEntity<String> processText(@RequestBody String text) {
        azureService.addTextToSet(text);
        return new ResponseEntity<>("Running", HttpStatus.ACCEPTED);
    }
}
