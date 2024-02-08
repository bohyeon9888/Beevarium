package b203.varium.openvidu.domain;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Role is required")
    private String role;

//    @NotNull(message = "Kurento options are required")
//    private KurentoOptionsDto kurentoOptions;
}
