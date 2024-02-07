package b203.varium.broadcasting.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReqDTO {
    private String broadcastingTitle;
    private String broadcastingImgUrl;
    private List<String> tagList;
}