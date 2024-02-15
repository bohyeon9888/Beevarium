package b203.varium.board.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class StationNoticeDTO {
    private int broadcastStationNoticeNo;
    private int broadcastStationNo;
    private String broadcastStationNoticeTitle;
    private String broadcastStationNoticeContent;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
