package b203.varium.video.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClipVideoDTO {

    private int id; // 방송국 아이디
    private int stationId;
    private String title;
    private String writerName;
    private int viewers;
    private String imgUrl;
    private Timestamp createdDate;
    private FileInfoDTO fileInfo;

}
