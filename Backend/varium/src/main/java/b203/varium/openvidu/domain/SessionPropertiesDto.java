package b203.varium.openvidu.domain;

import io.openvidu.java.client.MediaMode;
import io.openvidu.java.client.RecordingMode;
import io.openvidu.java.client.VideoCodec;
import lombok.Data;

@Data
public class SessionPropertiesDto {
    private MediaMode mediaMode;
    private RecordingMode recordingMode;
    private String customSessionId;
    private VideoCodec forcedVideoCodec;
    private boolean allowTranscoding;
    private RecordingPropertiesDto defaultRecordingProperties;
    private String mediaNode;

}
