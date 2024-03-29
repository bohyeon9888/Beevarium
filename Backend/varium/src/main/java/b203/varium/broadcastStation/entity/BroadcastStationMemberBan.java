package b203.varium.broadcastStation.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "broadcast_station_member_ban")
public class BroadcastStationMemberBan extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcasting_station_member_ban_no")
    private Integer broadcastStationMemberBanNo;

    @ManyToOne
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    private int broadcastStationNo;

    private int userNo;    // follwing


}
