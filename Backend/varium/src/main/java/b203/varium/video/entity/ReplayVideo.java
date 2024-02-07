package b203.varium.video.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "replay_video")
public class ReplayVideo extends Video {

    // `@Id` 어노테이션, video_no 제거
    // (상속받는 경우 기본 키(@Id)는 상위 클래스에서만 정의해야 함)

    @Column(name = "replay_video_no")
    private int replayVideoNo;

    @Column(name = "replay_video_text_url")
    private String replayVideoTextUrl;

    // Constructors, getters and setters
}
