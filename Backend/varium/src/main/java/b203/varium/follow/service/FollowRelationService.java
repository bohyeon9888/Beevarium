package b203.varium.follow.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.follow.dto.FollowDTO;
import b203.varium.follow.dto.FollowRespDTO;
import b203.varium.follow.dto.FollowerRespDTO;
import b203.varium.follow.entity.FollowRelation;
import b203.varium.follow.respository.FollowRelationRepository;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class FollowRelationService {

    private final FollowRelationRepository followRelationRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final UserRepository userRepository;

    public FollowRelationService(FollowRelationRepository followRelationRepository, BroadcastStationRepository broadcastStationRepository, UserRepository userRepository) {
        this.followRelationRepository = followRelationRepository;
        this.broadcastStationRepository = broadcastStationRepository;
        this.userRepository = userRepository;
    }

    // 새로운 FollowRelation 엔티티를 저장
    @Transactional
    public Map<String, String> saveFollowRelation(FollowDTO followDTO) {

        Map<String, String> resp = new HashMap<>();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        FollowRelation relation = new FollowRelation();
        String username = followDTO.getUsername();
        UserEntity user = userRepository.findByUsername(username);
        BroadcastStation station = broadcastStationRepository.findById(followDTO.getStationId());

        Map<String, String> errorCheckResult = checkForError(user, station);

        // 중복이 있는 경우나 스스로를 구독하는 경우 검사 결과 반환
        if (!errorCheckResult.isEmpty()) {
            return errorCheckResult;
        }

        relation.setBroadcastStation(station);
        relation.setFollower(user);
        relation.setCreatedDate(nowT);
        relation.setUpdatedDate(nowT);

        followRelationRepository.save(relation);
        log.debug("success saving follow relation");

        // 구독자 수 증가
        int nowS = station.getBroadcastStationFollowNum();
        station.setBroadcastStationFollowNum(nowS + 1);
        broadcastStationRepository.save(station);

        resp.put("status", "success");
        resp.put("msg", "Join Success");
        return resp;
    }

    private Map<String, String> checkForError(UserEntity user, BroadcastStation station) {
        Map<String, String> resp = new HashMap<>();

        // 자기 자신 구독
        if (user.getUsername().equals(station.getUser().getUsername())) {
            resp.put("status", "fail");
            resp.put("msg", "You can't subscribe yourself!");
            log.error("subscribe yourself!");
        } else if (followRelationRepository.existsByBroadcastStationAndFollower(station, user)) {
            resp.put("status", "fail");
            resp.put("msg", "You already subscribed it!");
            log.error("already subscribed it!");
        }

        return resp;
    }

    @Transactional(readOnly = true)
    public List<FollowerRespDTO> getFollowerList(String username) {
        List<FollowerRespDTO> result = new ArrayList<>();
        List<FollowRelation> resp = followRelationRepository.findAllByBroadcastStation_User_Username(username);

        for (FollowRelation relation : resp) {
            UserEntity user = relation.getFollower();
            FollowerRespDTO respDTO = new FollowerRespDTO();

            respDTO.setUserNo(user.getId());
            respDTO.setUsername(user.getUsername());
            respDTO.setUserProfile(user.getProfileUrl());
            result.add(respDTO);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<FollowRespDTO> getFollowList(String username) {
        List<FollowRespDTO> result = new ArrayList<>();
        List<FollowRelation> resp = followRelationRepository.findAllByFollower_Username(username);

        for (FollowRelation followRelation : resp) {
            BroadcastStation station = followRelation.getBroadcastStation();
            FollowRespDTO respDTO = new FollowRespDTO();

            respDTO.setStationNo(station.getId());
            respDTO.setStationTitle(station.getBroadcastStationTitle());
            respDTO.setStationProfile(station.getUser().getProfileUrl());
            result.add(respDTO);
        }

        return result;
    }

    @Transactional
    public HashMap<String, String> deleteFollow(String username, int stationId) {
        HashMap<String, String> result = new HashMap<>();
        BroadcastStation station = broadcastStationRepository.findById(stationId);
        UserEntity user = userRepository.findByUsername(username);

        if (followRelationRepository.existsByBroadcastStationAndFollower(station, user)) {
            FollowRelation relation = followRelationRepository.findByBroadcastStation_IdAndFollower_Username(stationId, username);
            followRelationRepository.delete(relation);

            int nowF = station.getBroadcastStationFollowNum();
            station.setBroadcastStationFollowNum(nowF - 1);
            broadcastStationRepository.save(station);

            result.put("status", "success");
            result.put("msg", "Success Unsubscribe!");
        } else {
            result.put("status", "fail");
            result.put("msg", "already unsubscribed!");
        }

        return result;
    }
}
