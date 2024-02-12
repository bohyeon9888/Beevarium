package b203.varium.broadcasting.dto;

import lombok.Data;

@Data
public class StationLiveDTO {
    private boolean isLive;
    private int broadcastingNo;
    private String broadcastingTitle;
    private String broadcastingThumbnail;
    private int viewers;
}
