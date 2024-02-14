package b203.varium.search.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.broadcasting.service.BroadcastingService;
import b203.varium.search.dto.LiveRespDTO;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final BroadcastingRepository broadcastingRepository;
    private final BroadcastingService broadcastingService;

    public Map<String, Object> searchStreamer(String keyword) {
        List<UserEntity> userList = userRepository.findAllByUsernameContaining(keyword);
        List<LiveRespDTO> result = new ArrayList<>();
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        if (userList.isEmpty()) {
            resp.put("status", "fail");
            data.put("msg", "no user");
            resp.put("data", data);
            return resp;
        }

        for (UserEntity user : userList) {
            log.info(user.getId());
            Broadcasting broadcasting = broadcastingRepository.findByBroadcastStation_User_Id(user.getId());
            log.info(broadcasting);
            if (broadcasting == null) continue;

            LiveRespDTO liveDTO = new LiveRespDTO();
            liveDTO.setStreamerId(user.getUserId());
            liveDTO.setViewers(broadcasting.getBroadcastingViewers());
            liveDTO.setStreamerName(user.getUsername());
            liveDTO.setProfileUrl(user.getProfileUrl());
            liveDTO.setBroadcastingImgUrl(broadcasting.getBroadcastingImgUrl());
            liveDTO.setBroadcastingNo(broadcasting.getId());
            liveDTO.setBroadcastingTitle(broadcasting.getBroadcastingTitle());
            liveDTO.setTags(broadcastingService.getTags(broadcasting.getId()));

            result.add(liveDTO);
        }

        resp.put("status", "success");
        data.put("result", result);
        resp.put("data", data);
        return resp;
    }
}
