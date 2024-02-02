package b203.varium.broadcastStation.service;


import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BroadcastStationService {

    private final BroadcastStationRepository broadcastStationRepository;
    private final UserRepository userRepository;

    @Autowired
    public BroadcastStationService(BroadcastStationRepository broadcastStationRepository,
                                   UserRepository userRepository) {
        this.broadcastStationRepository = broadcastStationRepository;
        this.userRepository = userRepository;
    }

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

        return broadcastStationRepository.findById(stationId);
    }

    public BroadcastStation createOrUpdateBroadcastStation(BroadcastStation broadcastStation) {
        // User 엔티티와 연결
        if (broadcastStation.getUserNo() != null) {
            UserEntity user = userRepository.findById(broadcastStation.getUserNo())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            broadcastStation.setUser(user); // User 엔티티 연결
        }

        return broadcastStationRepository.save(broadcastStation);
    }

    // BroadcastStation 저장
    public BroadcastStation saveBroadcastStation(BroadcastStation broadcastStation) {
        return broadcastStationRepository.save(broadcastStation);
    }

    // BroadcastStation 조회
    public BroadcastStation getBroadcastStationById(Integer broadcastStationNo) {
        return broadcastStationRepository.findById(broadcastStationNo)
                .orElseThrow(() -> new EntityNotFoundException("BroadcastStation not found with id: " + broadcastStationNo));
    }

//    public Optional<BroadcastStation> getBroadcastStationById(Integer id) {
//        return broadcastStationRepository.findById(id);
//    }

    // 모든 BroadcastStation 조회
    public List<BroadcastStation> getAllBroadcastStations() {
        return broadcastStationRepository.findAll();
    }


    public BroadcastStation updateBroadcastStation(BroadcastStation broadcastStation) {
        return broadcastStationRepository.save(broadcastStation);
    }

    public void deleteBroadcastStation(Integer id) {
        broadcastStationRepository.deleteById(id);
    }

    // 추가적인 비즈니스 로직 메서드 추가 가능
}
