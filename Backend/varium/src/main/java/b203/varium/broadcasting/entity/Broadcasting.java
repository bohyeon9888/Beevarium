package b203.varium.broadcasting.entity;

import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.hashtag.entity.HashTag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "broadcasting")
public class Broadcasting extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcasting_no")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
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

    @OneToMany(mappedBy = "broadcasting", cascade = CascadeType.REMOVE)
    private List<HashTag> hashTagList;
}
