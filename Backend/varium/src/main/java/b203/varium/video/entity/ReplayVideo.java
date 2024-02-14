package b203.varium.video.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "replay_video")
public class ReplayVideo extends Video {

    @Column(name = "replay_video_no")
    private int replayVideoNo;

    @NotNull(message = "비디오 url을 입력해야 합니다.")
    @Column(name = "replay_video_url")
    private String replayVideoUrl;

    // Constructors, getters and setters
}
