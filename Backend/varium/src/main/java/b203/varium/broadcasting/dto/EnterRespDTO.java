package b203.varium.broadcasting.dto;

import lombok.Data;

import java.util.List;

@Data
public class EnterRespDTO {
    private boolean isFollow;
    private int stationNo;
    private String streamerId;
    private String streamerName;
    private String streamerProfile;
    private String title;
    private int viewers;
    private List<String> tags;
    private String username;
}
