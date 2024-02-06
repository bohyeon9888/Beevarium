package b203.varium.openvidu.service;

import b203.varium.openvidu.domain.ConnectionPropertiesDto;
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

import java.util.Arrays;
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

        RecordingProperties recordingProperties = new RecordingProperties.Builder()
                .name(sessionPropertiesDto.getDefaultRecordingProperties().getName())
                .hasAudio(sessionPropertiesDto.getDefaultRecordingProperties().getHasAudio())
                .hasVideo(sessionPropertiesDto.getDefaultRecordingProperties().getHasVideo())
                .outputMode(sessionPropertiesDto.getDefaultRecordingProperties().getOutputMode())
                .recordingLayout(sessionPropertiesDto.getDefaultRecordingProperties().getRecordingLayout())
                .resolution(sessionPropertiesDto.getDefaultRecordingProperties().getResolution())
                .frameRate(sessionPropertiesDto.getDefaultRecordingProperties().getFrameRate())
                .shmSize(sessionPropertiesDto.getDefaultRecordingProperties().getShmSize())
                .mediaNode(sessionPropertiesDto.getDefaultRecordingProperties().getMediaNode())
                .build();

        SessionProperties properties = new SessionProperties.Builder()
                .mediaMode(sessionPropertiesDto.getMediaMode())
                .recordingMode(sessionPropertiesDto.getRecordingMode())
                .customSessionId(sessionPropertiesDto.getCustomSessionId())
                .forcedVideoCodec(sessionPropertiesDto.getForcedVideoCodec())
                .allowTranscoding(sessionPropertiesDto.isAllowTranscoding())
                .defaultRecordingProperties(recordingProperties)
                .mediaNode(sessionPropertiesDto.getMediaNode())
                .build();

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

    public ResponseEntity<String> connectionSession(String sessionId, ConnectionPropertiesDto connectionPropertiesDto) throws OpenViduJavaClientException, OpenViduHttpException {
        ConnectionType connectionType = Optional.ofNullable(connectionPropertiesDto.getType())
                .map(String::toUpperCase)
                .map(ConnectionType::valueOf)
                .orElse(ConnectionType.IPCAM); // 기본값으로 IPCAM을 사용

        OpenViduRole openViduRole = Optional.ofNullable(connectionPropertiesDto.getRole())
                .map(String::toUpperCase)
                .filter(role -> Arrays.asList("PUBLISHER", "MODERATOR").contains(role))
                .map(OpenViduRole::valueOf)
                .orElse(OpenViduRole.SUBSCRIBER); // 기본값으로 SUBSCRIBER를 사용

        log.info("connectionType={}", connectionType);
        log.info("openViduRole={}", openViduRole);

        KurentoOptions kurentoOptions = new KurentoOptions(
                connectionPropertiesDto.getKurentoOptions().getVideoMaxRecvBandwidth(),
                connectionPropertiesDto.getKurentoOptions().getVideoMinRecvBandwidth(),
                connectionPropertiesDto.getKurentoOptions().getVideoMaxSendBandwidth(),
                connectionPropertiesDto.getKurentoOptions().getVideoMinSendBandwidth(),
                connectionPropertiesDto.getKurentoOptions().getAllowedFilters().toArray(new String[0])
        );

        ConnectionProperties connectionProperties = new ConnectionProperties.Builder()
                .type(connectionType)
                .role(openViduRole)
                .data(connectionPropertiesDto.getData())
                .kurentoOptions(kurentoOptions)
                .build();

        Connection connection = openvidu.getActiveSession(sessionId).createConnection(connectionProperties);

        ConnectionResponseDto connectionResponseDto = new ConnectionResponseDto();
        connectionResponseDto.setConnectionId(connection.getConnectionId());
        connectionResponseDto.setConnectionToken(connection.getToken());
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
        boolean recorded = openvidu.getActiveSession(sessionId).isBeingRecorded();
        return new ResponseEntity<>(recorded, HttpStatus.OK);
    }

    public ResponseEntity<String> startRecordings(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        Recording recording = openvidu.startRecording(sessionId);
        Gson gson = new Gson();
        String json = gson.toJson(recording);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
