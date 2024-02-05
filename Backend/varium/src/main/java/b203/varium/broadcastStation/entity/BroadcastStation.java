package b203.varium.broadcastStation.entity;


import b203.varium.Record;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.user.entity.UserEntity;
import b203.varium.video.entity.Video;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
// Video와의 연관관계 추가
@Entity
@Getter
@Setter
@Table(name = "broadcast_station")
public class BroadcastStation extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_no")
    private int id; // broadcast_station_no를 기본 키로 사용

    @Column(name = "broadcast_station_title", nullable = false)
    private String broadcastStationTitle;

    @Column(name = "broadcast_station_follow_num", columnDefinition = "INT DEFAULT 0")
    private Integer broadcastStationFollowNum; // 기본값으로 0 설정


    @Column(name = "broadcast_station_noti_mention", columnDefinition = "VARCHAR(300) DEFAULT '~'")
    private String broadcastStationNotiMention; // 기본값으로 '~' 설정

    // User와 일대일 관계
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private UserEntity user;

    @OneToOne(mappedBy = "broadcastStation")
    private Broadcasting broadcasting;

    // Video와의 연관관계
    @OneToMany(mappedBy = "broadcastStation")
    private List<Video> videos;

    // 기타 필드 및 메소드...
    public void setUser(UserEntity user) {
        if (this.user != null) {
            this.user.setStation(null);
        }

        this.user = user;
        if (user != null) {
            user.setStation(this);
        }
    }

    public int getUserNo() {
        return user != null ? user.getId() : -1;
    }

}
