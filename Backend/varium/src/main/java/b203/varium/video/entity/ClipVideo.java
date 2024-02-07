package b203.varium.video.entity;

import b203.varium.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "clip_video")
public class ClipVideo extends Video {

    @Column(name = "clip_video_no")
    private int clipVideoNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private UserEntity user;

}
