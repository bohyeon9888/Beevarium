package b203.varium.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class LiveRespDTO {
    private String broadcastingTitle;
    private String broadcastingNo;
    private String broadcastingImgUrl;
    private int viewers;
    private List<String> hashtags;
}
