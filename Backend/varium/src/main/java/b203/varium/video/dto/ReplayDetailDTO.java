package b203.varium.video.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplayDetailDTO {
    private String title;
    private int viewers;
    private Timestamp createdDate;
    private String replayVideoUrl;
}
