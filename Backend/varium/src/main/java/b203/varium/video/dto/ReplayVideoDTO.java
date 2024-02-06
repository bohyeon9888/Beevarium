package b203.varium.video.dto;

import lombok.Data;

@Data
public class ReplayVideoDTO {
    private int id;
    private String title;
    private int viewers;
    private String imgUrl;
    private String replayVideoTextUrl; // 리플레이 비디오 전용 필드
    private int userNo; // 클립 비디오 생성 사용자 번호 전용 필드

    // getters and setters
}

