package b203.varium.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRequestDTO {

    @NotNull(message = "포인트가 비어있습니다.")
    private Integer point;
}
