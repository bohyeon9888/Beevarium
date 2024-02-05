package b203.varium.broadcasting.service;

import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import b203.varium.hashtag.entity.HashTag;
import b203.varium.hashtag.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BroadcastingService {

    private final BroadcastingRepository broadcastingRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final TagService tagService;

    public BroadcastingService(BroadcastingRepository broadcastingRepository, BroadcastStationRepository broadcastStationRepository, TagService tagService) {
        this.broadcastingRepository = broadcastingRepository;
        this.broadcastStationRepository = broadcastStationRepository;
        this.tagService = tagService;
    }

    @Transactional
    public Map<String, String> startBroadcasting(int stationId, String title, String url, String thumbnail, List<String> tagList) {
        Map<String, String> resp = new HashMap<>();
        Broadcasting data = new Broadcasting();
        data.setBroadcastStation(broadcastStationRepository.findById(stationId));
        data.setBroadcastingTitle(title);
        data.setBroadcastingUrl(url);
        data.setBroadcastingImgUrl(thumbnail);
        List<HashTag> hashTagList = tagService.insertHashTag(data, tagList);

        data.setHashTagList(hashTagList);
        broadcastingRepository.save(data);

        resp.put("status", "success");
        resp.put("msg", "Success Starting the live");
        return resp;
    }

    public void listBroadcasting(String username) {
        
    }

    @Transactional
    public Map<String, String> endBroadcasting(int stationId) {
        Map<String, String> resp = new HashMap<>();

        Broadcasting live = broadcastingRepository.findByBroadcastStation_Id(stationId);
        broadcastingRepository.delete(live);

        resp.put("status", "success");
        resp.put("msg", "Success Ending the live");
        return resp;
    }

}
