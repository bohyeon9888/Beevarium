package b203.varium.video.service;

import b203.varium.board.entity.BroadcastStationNotice;
import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.video.dto.ReplayDetailDTO;
import b203.varium.video.dto.VideoFileDTO;
import b203.varium.video.dto.ReplayVideoDTO;
import b203.varium.video.entity.ReplayVideo;
import b203.varium.video.repository.ReplayVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplayVideoService {

    private final ReplayVideoRepository replayVideoRepository;
    private final BroadcastStationRepository broadcastStationRepository;

    public List<ReplayVideoDTO> getReplayVideos(String streamerId) {
        int stationId = broadcastStationRepository.findByUser_UserId(streamerId).getId();
        List<ReplayVideo> list = replayVideoRepository.findAllByBroadcastStation_Id(stationId);
        List<ReplayVideoDTO> result = new ArrayList<>();

        for (ReplayVideo replay : list) {
            ReplayVideoDTO videoDTO = new ReplayVideoDTO();
            videoDTO.setId(replay.getId());
            videoDTO.setTitle(replay.getVideoTitle());
            videoDTO.setViewers(replay.getVideoViewers());
            videoDTO.setImgUrl(replay.getThumbnailUrl());
            videoDTO.setReplayVideoUrl(replay.getReplayVideoUrl());
            videoDTO.setCreatedDate(replay.getCreatedDate());

            result.add(videoDTO);
        }

        return result;
    }

    @Transactional
    public Map<String, Object> saveReplayInfo(String filePath, String username) {
        BroadcastStation station = broadcastStationRepository.findByUser_Username(username);
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");

        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        log.info(formatter.format(date));
        String[] path = filePath.split("/");
        String folderName = path[path.length -2];
        log.info("folderName = {}" , folderName);

        ReplayVideo replay = new ReplayVideo();
        replay.setBroadcastStation(station);
        replay.setVideoTitle(username + "의 " + formatter.format(date) + "다시보기");
        replay.setThumbnailUrl("https://b203-beevairum.s3.ap-northeast-2.amazonaws.com/" + folderName + "/" + folderName + ".jpg");
        log.info("thumbnailUrl = {}", replay.getThumbnailUrl());
        replay.setCodeName("V1");
        replay.setReplayVideoNo(replayVideoRepository.findMaxReplayVideoNo()+1);
        replay.setReplayVideoUrl(filePath);
        replay.setCreatedDate(nowT);
        replay.setUpdatedDate(nowT);

        replayVideoRepository.save(replay);

        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "success");
        Map<String, String> msg = new HashMap<>();
        msg.put("msg", "success to save replay");
        resp.put("data", msg);

        return resp;
    }

    public Map<String, Object> detailVideo(int videoNo) {
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> msg = new HashMap<>();

        ReplayVideo replay = replayVideoRepository.findById(videoNo);
        if (replay==null) {
            resp.put("status", "fail");
            msg.put("msg", "no data");
        }

        ReplayDetailDTO detail = new ReplayDetailDTO();
        detail.setReplayVideoUrl(replay.getReplayVideoUrl());
        detail.setTitle(replay.getVideoTitle());
        detail.setViewers(replay.getVideoViewers());
        detail.setCreatedDate(replay.getCreatedDate());

        resp.put("status", "success");
        msg.put("detail", detail);

        resp.put("data", msg);
        return resp;
    }
}

