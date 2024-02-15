package b203.varium.broadcasting.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.broadcasting.dto.EnterRespDTO;
import b203.varium.broadcasting.dto.ListRespDTO;
import b203.varium.broadcasting.dto.StationLiveDTO;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.chatting.dto.ChatDTO;
import b203.varium.chatting.service.ChatService;
import b203.varium.follow.dto.FollowRespDTO;
import b203.varium.follow.respository.FollowRelationRepository;
import b203.varium.follow.service.FollowRelationService;
import b203.varium.hashtag.entity.HashTag;
import b203.varium.hashtag.repository.HashTagRepository;
import b203.varium.hashtag.service.TagService;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
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
    private final FollowRelationRepository followRepository;
    private final UserRepository userRepository;
    private final ChatService chatService;

    @Transactional
    public Map<String, Object> startBroadcasting(String username, String title, List<String> tagList) {
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Broadcasting data = new Broadcasting();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        BroadcastStation station = broadcastStationRepository.findByUser_Username(username);
        UserEntity user = userRepository.findByUsername(username);

        if (broadcastingRepository.existsByBroadcastStation_Id(station.getId())) {
            result.put("msg", "already starting");
            resp.put("status", "fail");
            resp.put("data", result);
            return resp;
        }

        data.setBroadcastStation(station);
        data.setBroadcastingTitle(title);
        data.setBroadcastingImgUrl("https://b203-beevairum.s3.ap-northeast-2.amazonaws.com/thumbnail/none.png");
        data.setCreatedDate(nowT);
        data.setUpdatedDate(nowT);
        broadcastingRepository.save(data);

        List<HashTag> hashTagList = tagService.insertHashTag(data.getId(), tagList);

        data.setHashTagList(hashTagList);
        broadcastingRepository.save(data);

        result.put("streamerId", user.getUserId());
        resp.put("status", "success");
        resp.put("data", result);
        return resp;
    }

    public Map<String, Object> subscribeListBroadcasting(String username) {
        List<ListRespDTO> result = new ArrayList<>();
        List<FollowRespDTO> followList = followService.getFollowList(username);

        for (FollowRespDTO follow : followList) {
            BroadcastStation station = broadcastStationRepository.findById(follow.getStationNo());
            result.add(setRespDTO(station));
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

    public Map<String, Object> getBroadcasting() {
        List<Broadcasting> list = broadcastingRepository.findTop8ByOrderByBroadcastingViewersDesc();
        List<ListRespDTO> result = new ArrayList<>();

        for (Broadcasting live : list) {
            BroadcastStation station = live.getBroadcastStation();

            result.add(setRespDTO(station));
        }

        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        if (result.size() <= 0) {
            data.put("msg", "데이터가 없습니다.");
            resp.put("status", "fail");
            resp.put("data", data);
        } else {
            data.put("topList", result);
            resp.put("status", "success");
            resp.put("data", data);
        }

        return resp;
    }

    public StationLiveDTO getStationBroadcasting(int stationNo) {
        StationLiveDTO liveDTO = new StationLiveDTO();

        if (!broadcastingRepository.existsByBroadcastStation_Id(stationNo)) {
            liveDTO.setLive(false);
            liveDTO.setBroadcastingThumbnail("none");
            liveDTO.setBroadcastingTitle("none");
            return liveDTO;
        }

        Broadcasting broadcasting = broadcastingRepository.findByBroadcastStation_Id(stationNo);

        liveDTO.setLive(true);
        liveDTO.setBroadcastingNo(broadcasting.getId());
        liveDTO.setBroadcastingThumbnail("https://b203-beevairum.s3.ap-northeast-2.amazonaws.com/thumbnail/none.png");
        liveDTO.setViewers(broadcasting.getBroadcastingViewers());
        liveDTO.setBroadcastingTitle(broadcasting.getBroadcastingTitle());
        return liveDTO;
    }

    public ListRespDTO setRespDTO(BroadcastStation station) {
        ListRespDTO data = new ListRespDTO();
        int stationId = station.getId();
        Broadcasting live = broadcastingRepository.findByBroadcastStation_Id(stationId);
        if (live == null) {
            return null;
        }

        int broadcastingNo = live.getId();
        data.setBroadcastingNo(broadcastingNo);
        data.setStreamerName(station.getUser().getUsername());
        data.setStreamerId(station.getUser().getUserId());
        data.setProfileUrl(station.getUser().getProfileUrl());
        data.setLiveTitle(live.getBroadcastingTitle());
        data.setThumbnailUrl(live.getBroadcastingImgUrl());
        data.setViewer(live.getBroadcastingViewers());
        data.setTags(getTags(broadcastingNo));

        return data;
    }

    @Transactional
    public Map<String, Object> endBroadcasting(String username, List<ChatDTO> chatting) {
        UserEntity user = userRepository.findByUsername(username);

        int stationId = broadcastStationRepository.findByUser_Username(username).getId();
        Broadcasting live = broadcastingRepository.findByBroadcastStation_Id(stationId);
        broadcastingRepository.delete(live);

        return chatService.saveChatting(user.getUserId(), chatting);
    }

    @Transactional
    public Map<String, Object> enterBroadcasting(String username, String streamerId) {
        Map<String, Object> resp = new HashMap<>();

        BroadcastStation station = broadcastStationRepository.findByUser_UserId(streamerId);
        EnterRespDTO respDTO = new EnterRespDTO();
        Broadcasting broadcasting = broadcastingRepository.findByBroadcastStation_Id(station.getId());
        UserEntity user = userRepository.findByUsername(username);

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

        int nowV = broadcasting.getBroadcastingViewers();
        broadcasting.setBroadcastingViewers(nowV + 1);

        respDTO.setFollow(followRepository.existsByBroadcastStationAndFollower(station, user));
        respDTO.setStationNo(station.getId());
        respDTO.setStreamerId(streamerId);
        respDTO.setStreamerName(station.getUser().getUsername());
        respDTO.setStreamerProfile(station.getUser().getProfileUrl());
        respDTO.setTitle(broadcasting.getBroadcastingTitle());
        respDTO.setViewers(broadcasting.getBroadcastingViewers()+1);
        respDTO.setUsername(username);
        respDTO.setTags(getTags(broadcasting.getId()));
        broadcastingRepository.save(broadcasting);

        resp.put("data", respDTO);
        resp.put("status", "success");
        return resp;
    }

    public List<String> getTags(int BroadcastingNo) {
        List<HashTag> tagList = tagRepository.findAllByBroadcasting_Id(BroadcastingNo);
        List<String> tags = new ArrayList<>();
        for (HashTag hashTag : tagList) {
            tags.add(hashTag.getTag().getTagText());
        }

        return tags;
    }
}
