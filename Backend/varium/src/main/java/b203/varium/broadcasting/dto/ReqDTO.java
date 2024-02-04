package b203.varium.broadcasting.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReqDTO {
    private int stationId;
    private String broadcastingTitle;
    private String broadcastingUrl;
    private String broadcastingImgUrl;
    private List<String> tagList;
}