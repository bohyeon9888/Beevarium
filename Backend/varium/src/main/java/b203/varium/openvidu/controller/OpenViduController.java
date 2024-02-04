package b203.varium.openvidu.controller;

import b203.varium.openvidu.domain.request.ConnectionPropertiesDto;
import b203.varium.openvidu.domain.request.SessionPropertiesDto;
import b203.varium.openvidu.domain.response.ConnectionResponseDto;
import b203.varium.openvidu.service.OpenViduService;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/openvidu/api")
@RequiredArgsConstructor
public class OpenViduController {
    private final OpenViduService openViduService;

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getSessions() {
        return openViduService.getSessions();
    }

    @PostMapping("/sessions")
    public ResponseEntity<String> initializeSession(@Validated @RequestBody(required = false) SessionPropertiesDto sessionPropertiesDto) throws OpenViduJavaClientException, OpenViduHttpException {
        log.info("openViduSession={}", sessionPropertiesDto);
        return openViduService.initializeSession(sessionPropertiesDto);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openViduService.deleteSession(sessionId);
    }

    @PostMapping("/sessions/{sessionId}/connection")
    public ResponseEntity<ConnectionResponseDto> connectionSession(@PathVariable String sessionId, @Valid @RequestBody(required = false) ConnectionPropertiesDto connectionPropertiesDto) throws OpenViduJavaClientException, OpenViduHttpException {
        log.info("OpenViduConnection = {}", connectionPropertiesDto);
        log.info("sessionId = {}", sessionId);
        return openViduService.connectionSession(sessionId, connectionPropertiesDto);
    }

    @DeleteMapping("/sessions/{sessionId}/connection/{connectionId}")
    public ResponseEntity<String> connectionDeleteSession(@PathVariable String sessionId, @PathVariable String connectionId) throws OpenViduJavaClientException, OpenViduHttpException {
        log.info("sessionId={}", sessionId);
        log.info("connectionId={}", connectionId);
        return openViduService.connectionDeleteSession(sessionId, connectionId);
    }


    @GetMapping("/sessions/{sessionId}/connection/{connectionId}/subscribers")
    public ResponseEntity<List<String>> getSubscribers(@PathVariable String sessionId, @PathVariable String connectionId) {
        return openViduService.getSubscribers(sessionId, connectionId);
    }

    @GetMapping("/sessions/{sessionId}/connection")
    public ResponseEntity<List<Connection>> getConnections(@PathVariable String sessionId) {
        return openViduService.getConnections(sessionId);
    }


}
