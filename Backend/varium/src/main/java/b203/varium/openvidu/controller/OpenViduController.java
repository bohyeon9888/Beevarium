package b203.varium.openvidu.controller;

import b203.varium.openvidu.domain.ConnectionPropertiesDto;
import b203.varium.openvidu.domain.RecordingPropertiesDto;
import b203.varium.openvidu.domain.SessionPropertiesDto;
import b203.varium.openvidu.service.OpenViduService;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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

    private static ResponseEntity<String> getStringResponseEntity(BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                if (error instanceof FieldError fieldError) {
                    errorMessage.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("; ");
                } else {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                }
            }
            log.info("Validation errors: {}", errorMessage);
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @GetMapping("/sessions")
    public ResponseEntity<List<Session>> getSessions() {
        return openViduService.getSessions();
    }

    @PostMapping("/sessions")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> initializeSession(@Validated @RequestBody SessionPropertiesDto sessionPropertiesDto, BindingResult result) throws OpenViduJavaClientException, OpenViduHttpException {
        ResponseEntity<String> errorMessage = getStringResponseEntity(result);
        if (errorMessage != null) return errorMessage;

        log.info("openViduSession={}", sessionPropertiesDto);
        return openViduService.initializeSession(sessionPropertiesDto);
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openViduService.deleteSession(sessionId);
    }

    @PostMapping("/sessions/{sessionId}/connection")
    public ResponseEntity<String> connectionSession(@PathVariable String sessionId, @Validated @RequestBody(required = false) ConnectionPropertiesDto connectionPropertiesDto, BindingResult result) throws OpenViduJavaClientException, OpenViduHttpException {
        ResponseEntity<String> errorMessage = getStringResponseEntity(result);
        if (errorMessage != null) return errorMessage;

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

    @PatchMapping("/sessions/{sessionId}")
    public ResponseEntity<String> patchSessions(@PathVariable String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        return openViduService.patchSessions(sessionId);
    }

    @GetMapping("/sessions/{sessionId}/recorded")
    public ResponseEntity<Boolean> getRecorded(@PathVariable String sessionId) {
        return openViduService.getRecorded(sessionId);
    }

    @PostMapping("sessions/{sessionId}/recordings/start")
    public ResponseEntity<String> startRecordings(@PathVariable String sessionId, @Validated @RequestBody RecordingPropertiesDto recordingPropertiesDto, BindingResult result) throws OpenViduJavaClientException, OpenViduHttpException {
        ResponseEntity<String> errorMessage = getStringResponseEntity(result);
        if (errorMessage != null) return errorMessage;

        log.info("시작은 되?");
        log.info("sessionId = {}", sessionId);
        log.info("recordingProperties = {}", recordingPropertiesDto);
        return openViduService.startRecordings(sessionId, recordingPropertiesDto);
    }

}
