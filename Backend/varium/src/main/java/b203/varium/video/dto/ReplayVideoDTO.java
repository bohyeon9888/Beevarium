package b203.varium.video.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReplayVideoDTO {
    private int id;
    private String title;
    private int viewers;
    private String imgUrl;
    private Timestamp createdDate;
    private VideoFileDTO fileInfo;
    private String replayVideoTextUrl; // 리플레이 비디오 전용 필드
}

