package b203.varium.openvidu.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ConnectionResponseDto {
    private String connectionId;
    private String connectionToken;
}
