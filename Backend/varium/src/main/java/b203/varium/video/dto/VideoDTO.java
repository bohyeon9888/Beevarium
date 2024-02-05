package b203.varium.video.dto;

import lombok.Data;

@Data
public class VideoDTO {
    private int id;
    private String title;
    private int viewers;
    private String url;
    private String imgUrl;
    private String type; // 비디오 타입을 나타내는 필드 추가

    // getters and setters
}

