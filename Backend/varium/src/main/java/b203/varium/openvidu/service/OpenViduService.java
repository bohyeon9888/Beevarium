package b203.varium.openvidu.service;

import b203.varium.openvidu.domain.ConnectionPropertiesDto;
import b203.varium.openvidu.domain.RecordingPropertiesDto;
import b203.varium.openvidu.domain.SessionPropertiesDto;
import b203.varium.openvidu.dto.ConnectionResponseDto;
import com.google.gson.Gson;
import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OpenViduService {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    public ResponseEntity<String> initializeSession(SessionPropertiesDto sessionPropertiesDto) throws OpenViduJavaClientException, OpenViduHttpException {
        log.info("openviduSession={}", sessionPropertiesDto);

        Recording.OutputMode outputMode = Optional.ofNullable(sessionPropertiesDto.getDefaultRecordingProperties().getOutputMode())
                .map(String::toUpperCase)
                .map(Recording.OutputMode::valueOf)
                .orElse(Recording.OutputMode.COMPOSED_QUICK_START); // 기본값으로 COMPOSED_QUICK_START를 사용

        RecordingLayout recordingLayout = Optional.ofNullable(sessionPropertiesDto.getDefaultRecordingProperties().getRecordingLayout())
                .map(String::toUpperCase)
                .filter(layout -> layout.equals("CUSTOM"))
                .map(layout -> RecordingLayout.CUSTOM)
                .orElse(RecordingLayout.BEST_FIT); // 기본값으로 BEST_FIT를 사용

        RecordingProperties recordingProperties = new RecordingProperties.Builder()
                .name(sessionPropertiesDto.getDefaultRecordingProperties().getName())
                .hasAudio(sessionPropertiesDto.getDefaultRecordingProperties().getHasAudio())
                .hasVideo(sessionPropertiesDto.getDefaultRecordingProperties().getHasVideo())
                .outputMode(outputMode)
                .recordingLayout(recordingLayout)
                .resolution(sessionPropertiesDto.getDefaultRecordingProperties().getResolution())
                .frameRate(sessionPropertiesDto.getDefaultRecordingProperties().getFrameRate())
                .shmSize(sessionPropertiesDto.getDefaultRecordingProperties().getShmSize())
                .mediaNode(sessionPropertiesDto.getDefaultRecordingProperties().getMediaNode())
                .build();

        RecordingMode recordingMode = Optional.ofNullable(sessionPropertiesDto.getRecordingMode())
                .filter("Always"::equals)
                .map(mode -> RecordingMode.ALWAYS)
                .orElse(RecordingMode.MANUAL);

        SessionProperties properties = new SessionProperties.Builder()
                .mediaMode(MediaMode.ROUTED)
                .recordingMode(recordingMode)
                .customSessionId(sessionPropertiesDto.getCustomSessionId())
                .forcedVideoCodec(VideoCodec.VP8)
                .forcedVideoCodecResolved(VideoCodec.VP8)
                .allowTranscoding(sessionPropertiesDto.getAllowTranscoding())
                .defaultRecordingProperties(recordingProperties)
                .mediaNode(sessionPropertiesDto.getMediaNode())
                .build();

        log.info("properties = {}", properties);
        Session session = openvidu.createSession(properties);
        return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
    }

    public ResponseEntity<List<Session>> getSessions() {
        return new ResponseEntity<>(openvidu.getActiveSessions(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteSession(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        openvidu.getActiveSession(sessionId).close();
        return new ResponseEntity<>(sessionId, HttpStatus.OK);
    }

    public ResponseEntity<String> connectionSession(String sessionId, ConnectionPropertiesDto connectionPropertiesDto) throws OpenViduJavaClientException, OpenViduHttpException, MalformedURLException {

        ConnectionType connectionType = Optional.ofNullable(connectionPropertiesDto.getType())
                .filter("WEBRTC"::equals)
                .map(type -> ConnectionType.WEBRTC)
                .orElse(ConnectionType.IPCAM);

        OpenViduRole openViduRole = Optional.ofNullable(connectionPropertiesDto.getRole())
                .map(role -> {
                    switch (role) {
                        case "SUBSCRIBER":
                            return OpenViduRole.SUBSCRIBER;
                        case "MODERATOR":
                            return OpenViduRole.MODERATOR;
                        default:
                            return OpenViduRole.PUBLISHER;
                    }
                })
                .orElse(OpenViduRole.PUBLISHER);

        log.info("connectionType={}", connectionType);
        log.info("openViduRole={}", openViduRole);

        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(connectionType)
                .data(connectionPropertiesDto.getData())
                .record(true)
                .role(openViduRole)
                .adaptativeBitrate(true)
                .onlyPlayWithSubscribers(true)
                .networkCache(2000)
                .build();

        Connection connection = openvidu.getActiveSession(sessionId).createConnection(connectionProperties);

        ConnectionResponseDto connectionResponseDto = new ConnectionResponseDto(
                connection.getConnectionId(),
                connection.getToken());
        log.info("connectionResponseDto = {}", connectionResponseDto);
        Gson gson = new Gson();
        String json = gson.toJson(connectionResponseDto);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public ResponseEntity<String> connectionDeleteSession(String sessionId, String connectionId) throws OpenViduJavaClientException, OpenViduHttpException {
        openvidu.getActiveSession(sessionId).forceDisconnect(connectionId);
        return new ResponseEntity<>(connectionId, HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getSubscribers(String sessionId, String connectionId) {
        List<String> subscribers = openvidu.getActiveSession(sessionId).getConnection(connectionId).getSubscribers();
        log.info("subscribers = {}", subscribers);
        return new ResponseEntity<>(subscribers, HttpStatus.OK);
    }

    public ResponseEntity<List<Connection>> getConnections(String sessionId) {
        List<Connection> connections = openvidu.getActiveSession(sessionId).getConnections();
        return new ResponseEntity<>(connections, HttpStatus.OK);
    }


    public ResponseEntity<String> patchSessions(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        boolean success = openvidu.getActiveSession(sessionId).fetch();
        return new ResponseEntity<>(Boolean.toString(success), HttpStatus.OK);
    }

    public ResponseEntity<Boolean> getRecorded(String sessionId) {
        log.info("sessionId = {}", sessionId);
        boolean recorded = openvidu.getActiveSession(sessionId).isBeingRecorded();
        log.info("recorded = {}", recorded);
        return new ResponseEntity<>(recorded, HttpStatus.OK);
    }

    public ResponseEntity<String> startRecordings(String sessionId, RecordingPropertiesDto recordingPropertiesDto) throws OpenViduJavaClientException, OpenViduHttpException {

        Recording.OutputMode outputMode = Optional.ofNullable(recordingPropertiesDto.getOutputMode())
                .map(String::toUpperCase)
                .map(Recording.OutputMode::valueOf)
                .orElse(Recording.OutputMode.COMPOSED_QUICK_START); // 기본값으로 COMPOSED_QUICK_START를 사용

        RecordingLayout recordingLayout = Optional.ofNullable(recordingPropertiesDto.getRecordingLayout())
                .map(String::toUpperCase)
                .filter(layout -> layout.equals("CUSTOM"))
                .map(layout -> RecordingLayout.CUSTOM)
                .orElse(RecordingLayout.BEST_FIT); // 기본값으로 BEST_FIT를 사용


        RecordingProperties recordingProperties = new RecordingProperties.Builder()
                .name(recordingPropertiesDto.getName())
                .hasAudio(recordingPropertiesDto.getHasAudio())
                .hasVideo(recordingPropertiesDto.getHasVideo())
                .outputMode(outputMode)
                .recordingLayout(recordingLayout)
                .customLayout(recordingPropertiesDto.getCustomLayout())
                .resolution(recordingPropertiesDto.getResolution())
                .frameRate(recordingPropertiesDto.getFrameRate())
                .shmSize(recordingPropertiesDto.getShmSize())
                .ignoreFailedStreams(recordingPropertiesDto.getIgnoreFailedStreams())
                .mediaNode(recordingPropertiesDto.getMediaNode())
                .build();

        log.info("여기까지 되");
        log.info("recordingProperties = {}", recordingProperties);

        Recording recording = openvidu.startRecording(sessionId, recordingProperties);
        Gson gson = new Gson();
        String json = gson.toJson(recording);

        log.info("json = {}", json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    public ResponseEntity<String> startRecordingsforName(String sessionId, String name) throws OpenViduJavaClientException, OpenViduHttpException {
        Recording recording = openvidu.startRecording(sessionId, name);

        Gson gson = new Gson();
        String json = gson.toJson(recording);

        log.info("json = {}", json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

}
