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

    // `@Id` 어노테이션, video_no 제거
    // (상속받는 경우 기본 키(@Id)는 상위 클래스에서만 정의해야 함)

    @Column(name = "clip_video_no")
    private int ClipVideoNo;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private UserEntity user;

}
