package b203.varium.broadcastStation.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BroadcastStationChat extends Record {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_chat_no")
    private Integer broadcastStationChatNo;

    private Integer broadcastStationChatMemberNo;


    @OneToOne
    @JoinColumn(name = "broadcast_station_chat_member_no", referencedColumnName = "broadcast_station_chat_member_no")
    private BroadcastStationChatMember broadcastStationChatMember;

    private String broadcastStationChatText;


}
