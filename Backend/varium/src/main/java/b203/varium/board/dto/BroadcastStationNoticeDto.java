// BroadcastStationNoticeDto.java
package b203.varium.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BroadcastStationNoticeDto {

    @NotNull(message = "broadcastStationNo 값은 널이 될 수 없습니다")
    private Integer broadcastStationNo;
    private String title;
    private String content;
}
