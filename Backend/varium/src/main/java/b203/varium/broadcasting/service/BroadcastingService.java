package b203.varium.broadcasting.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.broadcasting.dto.EnterRespDTO;
import b203.varium.broadcasting.dto.ListRespDTO;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.follow.dto.FollowRespDTO;
import b203.varium.follow.service.FollowRelationService;
import b203.varium.hashtag.entity.HashTag;
import b203.varium.hashtag.repository.HashTagRepository;
import b203.varium.hashtag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BroadcastingService {

    private final BroadcastingRepository broadcastingRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final TagService tagService;
    private final HashTagRepository tagRepository;
    private final FollowRelationService followService;

    @Transactional
    public Map<String, Object> startBroadcasting(String username, String title, String thumbnail, List<String> tagList) {
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Broadcasting data = new Broadcasting();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        BroadcastStation station = broadcastStationRepository.findByUser_Username(username);

        if (broadcastingRepository.existsByBroadcastStation_Id(station.getId())) {
            result.put("msg", "already starting");
            resp.put("status", "fail");
            resp.put("data", result);
            return resp;
        }

        data.setBroadcastStation(station);
        data.setBroadcastingTitle(title);
        data.setBroadcastingImgUrl(thumbnail);
        data.setCreatedDate(nowT);
        data.setUpdatedDate(nowT);
        broadcastingRepository.save(data);

        List<HashTag> hashTagList = tagService.insertHashTag(data.getId(), tagList);

        data.setHashTagList(hashTagList);
        broadcastingRepository.save(data);

        result.put("streamerId", username);
        resp.put("status", "success");
        resp.put("data", result);
        return resp;
    }

    public Map<String, Object> subscribeListBroadcasting(String username) {
        List<ListRespDTO> result = new ArrayList<>();
        List<FollowRespDTO> followList = followService.getFollowList(username);

        for (FollowRespDTO follow : followList) {
            BroadcastStation station = broadcastStationRepository.findById(follow.getStationNo());
            ListRespDTO data = new ListRespDTO();

            data.setStreamerName(station.getUser().getUsername());
            data.setStreamerId(station.getUser().getUserId());
            data.setProfileUrl(station.getUser().getProfileUrl());

            int stationId = station.getId();
            Broadcasting live = broadcastingRepository.findByBroadcastStation_Id(stationId);
            if (live == null) {
                break;
            }
            data.setLiveTitle(live.getBroadcastingTitle());
            data.setThumbnailUrl(live.getBroadcastingImgUrl());
            data.setViewer(live.getBroadcastingViewers());

            result.add(data);
        }

        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        if (result.size() <= 0) {
            data.put("msg", "데이터가 없습니다.");
            resp.put("status", "fail");
            resp.put("data", data);
        } else {
            data.put("subscribeList", result);
            resp.put("status", "success");
            resp.put("data", data);
        }

        return resp;
    }

    @Transactional
    public Map<String, String> endBroadcasting(String username) {
        Map<String, String> resp = new HashMap<>();

        int stationId = broadcastStationRepository.findByUser_Username(username).getId();
        Broadcasting live = broadcastingRepository.findByBroadcastStation_Id(stationId);
        broadcastingRepository.delete(live);

        resp.put("status", "success");
        resp.put("msg", "Success Ending the live");
        return resp;
    }

    @Transactional
    public Map<String, Object> enterBroadcasting(String username, String streamerId) {
        Map<String, Object> resp = new HashMap<>();

        BroadcastStation station = broadcastStationRepository.findByUser_UserId(streamerId);
        EnterRespDTO respDTO = new EnterRespDTO();
        Broadcasting broadcasting = broadcastingRepository.findByBroadcastStation_Id(station.getId());

        if (broadcasting == null) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("msg", "존재하는 생방송이 없습니다.");
            resp.put("status", "fail");
            resp.put("data", msg);
            return resp;
        }
        if (username.equals(station.getUser().getUsername())) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("msg", "당신의 방송에 입장하실 수 없습니다.");
            resp.put("status", "fail");
            resp.put("data", msg);
            return resp;
        }

        respDTO.setStreamerId(streamerId);
        respDTO.setStreamerName(station.getUser().getUsername());
        respDTO.setStreamerProfile(station.getUser().getProfileUrl());
        respDTO.setViewers(broadcasting.getBroadcastingViewers()+1);
        respDTO.setUsername(username);

        List<HashTag> tagList = tagRepository.findAllByBroadcasting_Id(broadcasting.getId());
        List<String> tags = new ArrayList<>();
        for (HashTag hashTag : tagList) {
            tags.add(hashTag.getTag().getTagText());
        }

        respDTO.setTags(tags);

        resp.put("data", respDTO);
        resp.put("status", "success");
        return resp;
    }
}
