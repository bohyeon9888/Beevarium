package b203.varium.broadcastStation.dto;

import b203.varium.board.dto.BroadcastStationNoticeDto;
import b203.varium.video.dto.ClipVideoDTO;
import b203.varium.video.dto.ReplayVideoDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MyStationRespDTO {
    private Boolean isMine;
    private int stationNo;
    private String stationTitle;
    private String stationImg;
    private String userId;
    // 방송국 공지사항, 방송국 다시보기 비디오, 방송국 클립 비디오
    private List<BroadcastStationNoticeDto> noticeList;
    private List<ReplayVideoDTO> replayList;
    private List<ClipVideoDTO> clipList;
}
