package b203.varium.broadcasting.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.broadcasting.dto.ListRespDTO;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.follow.dto.FollowRespDTO;
import b203.varium.follow.service.FollowRelationService;
import b203.varium.hashtag.entity.HashTag;
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
    private final FollowRelationService followService;

    @Transactional
    public Map<String, String> startBroadcasting(String username, String title, String thumbnail, List<String> tagList) {
        Map<String, String> resp = new HashMap<>();
        Broadcasting data = new Broadcasting();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        BroadcastStation station = broadcastStationRepository.findByUser_Username(username);

        data.setBroadcastStation(station);
        data.setBroadcastingTitle(title);
        data.setBroadcastingImgUrl(thumbnail);
        data.setCreatedDate(nowT);
        data.setUpdatedDate(nowT);
        broadcastingRepository.save(data);

        List<HashTag> hashTagList = tagService.insertHashTag(data.getId(), tagList);

        data.setHashTagList(hashTagList);
        broadcastingRepository.save(data);

        resp.put("status", "success");
        resp.put("msg", "Success Starting the live");
        return resp;
    }

    public List<ListRespDTO> subscribeListBroadcasting(String username) {
        List<ListRespDTO> result = new ArrayList<>();
        List<FollowRespDTO> followList = followService.getFollowList(username);

        for (FollowRespDTO follow : followList) {
            BroadcastStation station = broadcastStationRepository.findById(follow.getStationNo());
            ListRespDTO data = new ListRespDTO();

            data.setStreamerName(station.getUser().getUsername());
            data.setProfileUrl(station.getUser().getProfileUrl());

            int stationId = station.getId();
            Broadcasting live = broadcastingRepository.findByBroadcastStation_Id(stationId);
            data.setLiveTitle(live.getBroadcastingTitle());
            data.setThumbnailUrl(live.getBroadcastingImgUrl());
            data.setViewer(live.getBroadcastingViewers());

            result.add(data);
        }

        return result;
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

}
