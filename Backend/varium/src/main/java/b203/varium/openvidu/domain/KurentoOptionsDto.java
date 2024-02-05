package b203.varium.openvidu.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class KurentoOptionsDto {
    @NotNull(message = "videoMaxRecvBandwidth 값이 없습니다")
    @Min(value = 0, message = "videoMaxRecvBandwidth값은 0 보다 커야됩니다")
    private Integer videoMaxRecvBandwidth;

    @NotNull(message = "videoMinRecvBandwidth 값이 없습니다")
    @Min(value = 0, message = "videoMinRecvBandwidth값은 0보다 커야 됩니다")
    private Integer videoMinRecvBandwidth;

    @NotNull(message = "videoMaxSendBandwidth 값이 없습니다")
    @Min(value = 0, message = "videoMaxSendBandwidth 0 보다 커야됩니다")
    private Integer videoMaxSendBandwidth;

    @NotNull(message = "videoMinSendBandwidth 값이 없습니다")
    @Min(value = 0, message = "videoMinSendBandwidth 0 보다 커야됩니다")
    private Integer videoMinSendBandwidth;

    private List<String> allowedFilters; // null이 허용될 경우 검증이 필요 없음
}
