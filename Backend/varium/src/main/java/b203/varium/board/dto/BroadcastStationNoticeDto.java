// BroadcastStationNoticeDto.java
package b203.varium.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BroadcastStationNoticeDto {
//    @NotNull(message = "broadcastStationNo 값은 널이 될 수 없습니다")
    private int broadcastStationNo;
    private String broadcastStationNoticeTitle;
    private String broadcastStationNoticeContent;
}
