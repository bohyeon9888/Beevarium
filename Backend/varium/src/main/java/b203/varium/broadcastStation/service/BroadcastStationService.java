package b203.varium.broadcastStation.service;


import b203.varium.board.dto.BroadcastStationNoticeDto;
import b203.varium.board.service.BroadcastStationNoticeService;
import b203.varium.broadcastStation.dto.MyStationRespDTO;
import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.broadcasting.service.BroadcastingService;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import b203.varium.video.service.ClipVideoService;
import b203.varium.video.service.ReplayVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import static org.hibernate.query.sqm.tree.SqmNode.log;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BroadcastStationService {

    private final BroadcastStationRepository stationRepository;
    private final UserRepository userRepository;
    private final BroadcastStationNoticeService stationNoticeService;
    private final ReplayVideoService replayVideoService;
    private final BroadcastingService broadcastingService;

    public BroadcastStation getBroadcastStationWithDetails(int stationId) {

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

    public Map<String, Object> viewMyStation(String streamerId, String username) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        MyStationRespDTO respDTO = new MyStationRespDTO();
        UserEntity streamer = userRepository.findByUserId(streamerId);

        if (!stationRepository.existsByUser_UserId(streamerId)) {
            data.put("msg", "존재하지 않는 방송국입니다.");
            result.put("data", data);
            result.put("status", "fail");
        } else {
            result.put("status", "success");
            BroadcastStation station = stationRepository.findByUser_UserId(streamerId);
            respDTO.setStationNo(station.getId());
            respDTO.setStationTitle(station.getBroadcastStationTitle());
            respDTO.setUserId(streamerId);
            respDTO.setUserName(streamer.getUsername());
            respDTO.setStationFollower(station.getBroadcastStationFollowNum());
//            respDTO.setIsMine(username.equals(station.getUser().getUsername()));
            boolean isMine = Objects.equals(username, Optional.ofNullable(station).map(s -> s.getUser()).map(u -> u.getUsername()).orElse(null));
            respDTO.setIsMine(isMine);

            respDTO.setStationImg(streamer.getProfileUrl());
            List<BroadcastStationNoticeDto> noticeList = stationNoticeService.findNoticesByStationId(station.getId());
            if (noticeList.size() <= 0) {
                respDTO.setFirstNotice(null);
            } else {
                respDTO.setFirstNotice(noticeList.get(0));
            }
            respDTO.setReplayList(replayVideoService.getReplayVideos(streamerId));
            respDTO.setLiveInfo(broadcastingService.getStationBroadcasting(station.getId()));

            data.put("stationInfo", respDTO);
            result.put("data", data);
        }

        return result;
    }

}

