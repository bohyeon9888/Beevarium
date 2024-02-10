package b203.varium.azure.controller;

import b203.varium.azure.service.AzureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Process text", description = "Add text to processing set and return 'Running' status", responses = {
            @ApiResponse(responseCode = "202", description = "Accepted", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> processText(@RequestBody String text) {
        azureService.addTextToSet(text);
        return new ResponseEntity<>("Running", HttpStatus.ACCEPTED);
    }
}
