package b203.varium.broadcasting.entity;

import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "broadcasting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Broadcasting extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcasting_no")
    private int id;

    @OneToOne
    @JoinColumn(name = "broadcast_station_no", referencedColumnName = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    @Column(name = "broadcasting_title")
    private String broadcastingTitle;

    @Column(name = "broadcasting_viewers")
    @ColumnDefault("0")
    private int broadcastingViewers;

    @Column(name = "broadcasting_url")
    private String broadcastingUrl;

    @Column(name = "broadcasting_img_url")
    private String broadcastingImgUrl;

    @Builder
    public Broadcasting(BroadcastStation station, String broadcastingTitle, String broadcastingUrl, String broadcastingImgUrl) {
        this.broadcastStation = station;
        this.broadcastingTitle = broadcastingTitle;
        this.broadcastingUrl = broadcastingUrl;
        this.broadcastingImgUrl = broadcastingImgUrl;
    }
}
