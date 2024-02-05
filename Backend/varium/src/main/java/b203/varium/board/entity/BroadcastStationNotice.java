package b203.varium.board.entity;

import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "broadcast_station_notice")
public class BroadcastStationNotice extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_notice_no")
    private Integer broadcastStationNoticeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    private String broadcastStationNoticeTitle;

    @Column(name = "broadcast_station_notice_content")
    private String broadcastStationNoticeContent;


}
