package b203.varium.broadcastStation.dto;

import b203.varium.board.dto.StationNoticeDTO;
import b203.varium.broadcasting.dto.StationLiveDTO;
import b203.varium.video.dto.ReplayVideoDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.SimpleTimeZone;

@Data
@RequiredArgsConstructor
public class MyStationRespDTO {
    private Boolean isMine;
    private int stationNo;
    private String stationTitle;
    private String stationImg;
    private String userId;
    private String userName;
    private int stationFollower;
    // 방송국 공지사항, 방송국 다시보기 비디오, 방송국 클립 비디오
    private StationNoticeDTO firstNotice;
    private List<ReplayVideoDTO> replayList;
    private StationLiveDTO liveInfo;

}
