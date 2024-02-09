package b203.varium.video.dto;

import b203.varium.Record;
import lombok.Data;

@Data
public class VideoFileDTO extends Record {
    private String filepath;
    private String originFileName;
    private String saveFileName;
}
