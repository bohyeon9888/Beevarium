package b203.varium.video.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC) //컨트롤러에서 써야 해서 PUBLIC으로 함
@AllArgsConstructor
public class VideoDTO {
    private Integer id;
    private String title;
    private Integer viewers;
    private String imgUrl;
    private String replayVideoTextUrl; // 리플레이 비디오 전용 필드
    private Integer userNo; // 클립 비디오 생성 사용자 번호 전용 필드

    // getters and setters
}

