package b203.varium.broadcasting.dto;

import lombok.Data;

@Data
public class ListRespDTO {
    private String streamerName;
    private String liveTitle;
    private String profileUrl;
    private String thumbnailUrl;
    private int viewer;
}