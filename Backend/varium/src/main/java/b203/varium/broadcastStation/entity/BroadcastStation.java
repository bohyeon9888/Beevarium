package b203.varium.broadcastStation.entity;


import b203.varium.Record;
import b203.varium.board.entity.BroadcastStationNotice;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.follow.entity.FollowRelation;
import b203.varium.user.entity.User;
import b203.varium.video.entity.Video;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class BroadcastStation extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_no")
    private Integer broadcastStationNo; // broadcast_station_no를 기본 키로 사용

    @Column(name = "broadcast_station_title", nullable = false)
    private String broadcastStationTitle;

    @Column(name = "broadcast_station_follow_num", columnDefinition = "INT DEFAULT 0")
    private Integer broadcastStationFollowNum; // 기본값으로 0 설정

    @Column(name = "broadcast_station_noti_mention", columnDefinition = "VARCHAR(255) DEFAULT '~'")
    private String broadcastStationNotiMention; // 기본값으로 '~' 설정

    /**
     * 연관관계 맴핑
     */

    @OneToMany(mappedBy = "broadcastStation")
    private List<FollowRelation> followers; // 팔로우 관계 엔티티와의 연관 관계

    @OneToMany(mappedBy = "broadcastStation")
    private List<BroadcastStationMemberBan> banList;

    @OneToMany(mappedBy = "broadcastStation")
    private List<BroadcastStationNotice> notices;

//    @ManyToOne
//    @JoinColumn(name = "user_no", nullable = false)
//    private User user;
    // User와 일대일 관계
    @OneToOne
    @JoinColumn(name = "user_no", referencedColumnName = "user_no")
    private User user;

    //연관된 User 엔티티의 기본 키(userNo)를 반환하는 역할을 하는 메서드
    public Integer getUserNo() {
        return user != null ? user.getUserNo() : null;
    }

    @OneToOne(mappedBy = "broadcastStation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BroadcastStationAdmin admin;

    //Broadcasting과의 연관관계 (mappedBy = "broadcastStation",삭제함)
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Broadcasting broadcasting;

//    // Video와의 연관관계
    @OneToMany(mappedBy = "broadcastStation")
    private List<Video> videos;

//    // BroadcastStation과 연관된 ReplayVideo 리스트
//    @Setter
//    @OneToMany(mappedBy = "broadcastStation", cascade = CascadeType.ALL)
//    private List<ReplayVideo> replayVideos;
//
//    // BroadcastStation과 연관된 ClipVideo 리스트
//    @Setter
//    @OneToMany(mappedBy = "broadcastStation", cascade = CascadeType.ALL)
//    private List<ClipVideo> clipVideos;

    // BroadcastStation과 연관된 BroadcastStationMemberBan 리스트
    @Setter
    @OneToMany(mappedBy = "broadcastStation", cascade = CascadeType.ALL)
    private List<BroadcastStationMemberBan> memberBans;

    // 기타 필드 및 메소드...

}
