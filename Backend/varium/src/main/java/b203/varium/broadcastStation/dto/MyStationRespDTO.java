package b203.varium.broadcastStation.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MyStationRespDTO {
    private Boolean isMine;
    private String userId;
    private String stationTitle;
    // 방송국 공지사항, 방송국 다시보기 비디오, 방송국 클립 비디오
}
