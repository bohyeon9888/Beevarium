package b203.varium.video.entity;

import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "video")
public abstract class Video extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_no")
    private int id;

    // fk인 broadcast_station_no
    // 방송국 엔티티와의 관계를 정의
    @ManyToOne
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    @Column(name = "video_title")
    private String videoTitle;

    @Column(name = "video_viewers")
    private int videoViewers = 0;

    @Column(name = "video_img_url")
    private String videoImgUrl;

    @OneToOne(mappedBy = "video")
    private VideoFile file;

    // Constructors, getters and setters
}
