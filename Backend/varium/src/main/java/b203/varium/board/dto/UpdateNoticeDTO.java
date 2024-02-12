package b203.varium.board.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateNoticeDTO {
//    @NotNull(message = "broadcastStationNo 값은 널이 될 수 없습니다")
    private int broadcastStationNo;
    private String broadcastStationNoticeTitle;
    private String broadcastStationNoticeContent;
}
