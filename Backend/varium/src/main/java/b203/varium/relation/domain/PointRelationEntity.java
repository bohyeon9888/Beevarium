package b203.varium.relation.domain;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "point_relation")
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PointRelationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_relation_no")
    private Integer pointRelationNo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, optional = false)
    @JoinColumn(name = "user_no")
    private UserEntity user;

    @Column(name = "point_relation_point")
    private Integer pointRelationPoint;
}
