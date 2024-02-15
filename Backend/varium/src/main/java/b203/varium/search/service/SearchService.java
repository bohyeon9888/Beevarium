package b203.varium.search.service;

import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.broadcasting.service.BroadcastingService;
import b203.varium.hashtag.entity.HashTag;
import b203.varium.hashtag.entity.TagEntity;
import b203.varium.hashtag.repository.HashTagRepository;
import b203.varium.hashtag.repository.TagRepository;
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
    private final TagRepository tagRepository;
    private final HashTagRepository hashTagRepository;
    private final BroadcastingRepository broadcastingRepository;
    private final BroadcastingService broadcastingService;

    public Map<String, Object> searchTag(String keyword) {
        List<TagEntity> tagList = tagRepository.findAllByTagTextContaining(keyword);
        List<Broadcasting> broadcastingList = new ArrayList<>();
        List<LiveRespDTO> result = new ArrayList<>();
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> data = new HashMap<>();

        if (tagList.isEmpty()) {
            resp.put("status", "fail");
            data.put("result", result);
            resp.put("data", data);
            return resp;
        }

        for (TagEntity tag : tagList) {
            log.info(tag.getTagText());
            List<HashTag> hashTagList = hashTagRepository.findAllByTag_Id(tag.getId());

            for (HashTag hashtag : hashTagList) {
                Broadcasting broadcasting = hashtag.getBroadcasting();
                if (!broadcastingList.contains(broadcasting)) {
                    broadcastingList.add(broadcasting);
                }
            }
        }

        for (Broadcasting broadcasting : broadcastingList) {
            LiveRespDTO liveDTO = new LiveRespDTO();
            UserEntity user = broadcasting.getBroadcastStation().getUser();

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
