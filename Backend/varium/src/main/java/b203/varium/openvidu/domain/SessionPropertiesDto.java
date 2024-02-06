package b203.varium.openvidu.domain;

import io.openvidu.java.client.MediaMode;
import io.openvidu.java.client.RecordingMode;
import io.openvidu.java.client.VideoCodec;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SessionPropertiesDto {
    private MediaMode mediaMode;
    private RecordingMode recordingMode;

    @NotNull(message = "커스텀 세션 아이디 값은 필수 값입니다")
    private String customSessionId;
    private VideoCodec forcedVideoCodec;
    private boolean allowTranscoding;
    private RecordingPropertiesDto defaultRecordingProperties;
    private String mediaNode;

}
