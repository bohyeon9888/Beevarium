package b203.varium.openvidu.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ConnectionPropertiesDto {
    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Data is required")
    private String data;

    @NotNull(message = "Record flag is required")
    private Boolean record;

    @NotBlank(message = "Role is required")
    private String role;

    @NotNull(message = "Kurento options are required")
    private KurentoOptionsDto kurentoOptions;
}
