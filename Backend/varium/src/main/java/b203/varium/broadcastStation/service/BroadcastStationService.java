package b203.varium.broadcastStation.service;


import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.hibernate.query.sqm.tree.SqmNode.log;


@Service
@RequiredArgsConstructor
public class BroadcastStationService {

    private final BroadcastStationRepository stationRepository;

    public BroadcastStation getBroadcastStationWithDetails(int stationId) {
        // 여기에서 BroadcastStation을 조회하고 관련된 데이터를 모두 가져옵니다.
        //        BroadcastStation station = broadcastStationRepository.findById(stationId).orElseThrow(() -> new RuntimeException("Station not found"));
//
//        // 연관된 데이터 조회
//        List<BroadcastStationNotice> notices = noticeRepository.findByBroadcastStation(station);
//        List<ReplayVideo> replayVideos = replayVideoRepository.findByBroadcastStation(station);
//        List<ClipVideo> clipVideos = clipVideoRepository.findByBroadcastStation(station);
//        List<BroadcastStationMemberBan> bans = memberBanRepository.findByBroadcastStation(station);
//
//        // 관련 데이터를 BroadcastStation 객체에 추가
//        station.setNotices(notices);
//        station.setReplayVideos(replayVideos);
//        station.setClipVideos(clipVideos);
//        station.setMemberBans(bans);

        return stationRepository.findById(stationId);
    }

    @Transactional
    public BroadcastStation createBroadcastStation(UserEntity user) {
        // User 엔티티와 연결
        BroadcastStation station = new BroadcastStation();
        String username = user.getUsername();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        station.setBroadcastStationTitle(username + "의 방송국입니다.");
        station.setBroadcastStationFollowNum(0);
        station.setBroadcastStationNotiMention(username + "이 방송을 시작했습니다!");
        station.setCreatedDate(nowT);
        station.setUpdatedDate(nowT);
        station.setUser(user);

        stationRepository.save(station);
        log.debug("success creating BroadStation");

        return station;
    }

}

