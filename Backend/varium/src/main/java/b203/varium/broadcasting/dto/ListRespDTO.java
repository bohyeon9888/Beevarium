package b203.varium.broadcasting.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListRespDTO {
    private int broadcastingNo;
    private String streamerName;
    private String streamerId;
    private String liveTitle;
    private String profileUrl;
    private String thumbnailUrl;
    private int viewer;
    private List<String> tags;
}