package b203.varium.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class LiveRespDTO {
    private String streamerName;
    private String streamerId;
    private String profileUrl;
    private String broadcastingTitle;
    private int broadcastingNo;
    private String broadcastingImgUrl;
    private int viewers;
    private List<String> tags;
}
