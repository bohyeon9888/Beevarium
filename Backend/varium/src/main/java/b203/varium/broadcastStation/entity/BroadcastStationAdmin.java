package b203.varium.broadcastStation.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "broad_station_admin")
public class BroadcastStationAdmin extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_admin_no")
    private int id;

    private Integer broadcastStationNo;

    @OneToOne
    @JoinColumn(name = "broadcast_station_no", referencedColumnName = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    private Integer broadcastStationAdminState;

}
