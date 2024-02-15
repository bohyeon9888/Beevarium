package b203.varium.openvidu.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SessionPropertiesDto {

    private String recordingMode;
    private RecordingPropertiesDto defaultRecordingProperties;

    @NotNull(message = "커스텀 세션 아이디 값은 필수 값입니다")
    private String customSessionId;
    private String mediaNode;
    private Boolean allowTranscoding;
}
