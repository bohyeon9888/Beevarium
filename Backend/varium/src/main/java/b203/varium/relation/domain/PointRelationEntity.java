package b203.varium.relation.domain;

import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "point_relation")
public class PointRelationEntity extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_relation_no")
    private int pointRelationNo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "user_no")
    private UserEntity user;

    @Column(name = "point_relation_point")
    private int pointRelationPoint;

}
