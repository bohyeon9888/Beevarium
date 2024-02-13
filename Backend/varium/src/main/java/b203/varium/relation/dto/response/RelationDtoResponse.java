package b203.varium.relation.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RelationDtoResponse {

    @NotNull(message = "받는 사람이 비어 있을 수 는 없습니다")
    private Integer broadcastStationNo;
    @NotNull(message = "보내는 금액이 비어 있을 수는 없습니다")
    private Integer price;
}
