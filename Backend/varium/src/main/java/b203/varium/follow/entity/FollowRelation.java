package b203.varium.follow.entity;


import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "follow_relation")
public class FollowRelation extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_relation_no")
    private Integer id; // follow_relation_no를 기본 키로 사용

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "broadcast_station_no", nullable = false) // 외래 키 지정
    private BroadcastStation broadcastStation; // 방송국과의 연관 관계

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private UserEntity follower;

    // 기타 필드 및 메서드
}
