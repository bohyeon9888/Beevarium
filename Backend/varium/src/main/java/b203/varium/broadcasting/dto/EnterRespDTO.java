package b203.varium.broadcasting.dto;

import lombok.Data;

import java.util.List;

@Data
public class EnterRespDTO {
    private String streamerId;
    private String streamerName;
    private String streamerProfile;
    private int viewers;
    private List<String> tags;
    private String username;

}
