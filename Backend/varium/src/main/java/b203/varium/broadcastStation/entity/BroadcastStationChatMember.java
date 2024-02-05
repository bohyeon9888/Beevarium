package b203.varium.broadcastStation.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BroadcastStationChatMember extends Record {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broadcast_station_chat_member_no")
    private Integer broadcastStationChatMemberNo;

    @OneToOne(mappedBy = "broadcastStationChatMember", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BroadcastStationChat broadcastStationChat;

}
