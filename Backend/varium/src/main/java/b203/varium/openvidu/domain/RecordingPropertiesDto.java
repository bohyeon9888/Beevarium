package b203.varium.openvidu.domain;

import io.openvidu.java.client.Recording;
import io.openvidu.java.client.RecordingLayout;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordingPropertiesDto {

    private String name;

    @NotNull(message = "오디오 유무를 설정해야 합니다")
    private Boolean hasAudio;

    @NotNull(message = "비디오 유무를 설정해야 합니다")
    private Boolean hasVideo;

    @NotNull(message = "출력 모드를 설정해야 합니다")
    private Recording.OutputMode outputMode;

    @NotNull(message = "녹화 레이아웃을 설정해야 합니다")
    private RecordingLayout recordingLayout;

    @NotBlank(message = "해상도 설정은 필수입니다")
    private String resolution;

    @NotNull(message = "프레임 레이트는 비어 있을 수 없습니다")
    @Min(value = 1, message = "프레임 레이트는 1 이상이어야 합니다")
    private Integer frameRate;

    @NotNull(message = "공유 메모리 크기는 비어 있을 수 없습니다")
    @Min(value = 1, message = "공유 메모리 크기는 1 이상이어야 합니다")
    private Long shmSize;

    private String customLayout;
    private Boolean ignoreFailedStreams;
    private String mediaNode;
}
