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

    @Column(name = "replay_video_no")
    private int replayVideoNo;

    @Column(name = "replay_video_text_url")
    private String replayVideoTextUrl;

    // Constructors, getters and setters
}
