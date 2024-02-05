package b203.varium.video.entity;

import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Video extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoNo;

    //fk인 broadcast_station_no
    // 방송국 엔티티와의 관계를 정의
    @ManyToOne
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    private String videoTitle;
    private Integer videoViewers = 0;
    private String videoUrl;
    private String videoImgUrl;

    // Constructors, getters and setters
}
