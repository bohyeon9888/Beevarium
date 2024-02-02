package b203.varium.board.entity;

import com.pjt.beevarium.Record;
import com.pjt.beevarium.broadcastStation.entity.BroadcastStation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BroadcastStationNotice extends Record {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_notice_no")
    private Integer broadcastStationNoticeNo;

    private Integer broadcastStationNo;

    @ManyToOne
    @JoinColumn(name = "broadcast_station_no")
    private BroadcastStation broadcastStation;

    private String broadcastStationNoticeTitle;

    private String broadcastStationNoticeContent;


}
