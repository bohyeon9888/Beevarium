package b203.varium.video.service;

import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.video.dto.FileInfoDTO;
import b203.varium.video.dto.ReplayVideoDTO;
import b203.varium.video.entity.FileEntity;
import b203.varium.video.entity.ReplayVideo;
import b203.varium.video.repository.ReplayVideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplayVideoService {

    private final ReplayVideoRepository replayVideoRepository;
    private final BroadcastStationRepository broadcastStationRepository;

    public List<ReplayVideoDTO> getReplayVideos(String streamerid) {
        int stationId = broadcastStationRepository.findByUser_UserId(streamerid).getId();
        List<ReplayVideo> list = replayVideoRepository.findAllByBroadcastStation_Id(stationId);
        List<ReplayVideoDTO> result = new ArrayList<>();

        for (ReplayVideo replay : list) {
            ReplayVideoDTO videoDTO = new ReplayVideoDTO();
            videoDTO.setId(replay.getId());
            videoDTO.setTitle(replay.getVideoTitle());
            videoDTO.setViewers(replay.getVideoViewers());
            videoDTO.setReplayVideoTextUrl("none");
            videoDTO.setCreatedDate(replay.getCreatedDate());
            videoDTO.setImgUrl(replay.getVideoImgUrl());

            FileEntity file = replay.getFile();
            FileInfoDTO fileData = new FileInfoDTO();
            fileData.setFilepath(file.getFilePath());
            fileData.setOriginFileName(file.getOriginName());
            fileData.setSaveFileName(file.getSavedName());

            videoDTO.setFileInfo(fileData);
            System.out.println(videoDTO);
            result.add(videoDTO);
        }

        return result;
    }


    //ReplayVideo 엔티티 인스턴스를 받아 데이터베이스에 저장하거나 업데이트
    public ReplayVideo saveReplayVideo(ReplayVideo replayVideo) {
        return replayVideoRepository.save(replayVideo);
    }

    //특정 ID를 가진 ReplayVideo 엔티티를 데이터베이스에서 찾아 반환
    public ReplayVideo getReplayVideoById(Integer replayVideoNo) {
        return replayVideoRepository.findById(replayVideoNo)
                .orElseThrow(() -> new IllegalArgumentException("ReplayVideo not found with id: " + replayVideoNo));
    }
//    public Optional<ReplayVideo> getReplayVideoById(Integer id) {
//        return replayVideoRepository.findById(id);
//    }

    //데이터베이스에 저장된 모든 ReplayVideo 엔티티를 리스트로 반환 (조회)
    public List<ReplayVideo> getAllReplayVideos() {
        return replayVideoRepository.findAll();
    }

    //특정 ID를 가진 ReplayVideo 엔티티를 데이터베이스에서 삭제
    public void deleteReplayVideo(Integer id) {
        replayVideoRepository.deleteById(id);
    }

// ReplayVideoService.java 내의 추가 메소드 구현 예시

//    public List<ReplayVideo> findAllByBroadcastStationNo(Integer broadcastStationNo) {
//        // 방송국 번호에 해당하는 모든 ReplayVideo 조회 로직 구현
//        // 이 부분은 ReplayVideo 엔티티와 방송국을 연결하는 로직에 따라 달라질 수 있습니다.
//        return replayVideoRepository.findAllByBroadcastStation_Id(broadcastStationNo);
//    }

    public ReplayVideo updateReplayVideo(Integer replayVideoNo, ReplayVideo replayVideoDetails) {
        ReplayVideo replayVideo = replayVideoRepository.findById(replayVideoNo)
                .orElseThrow(() -> new IllegalArgumentException("ReplayVideo not found with id: " + replayVideoNo));

        // Video 클래스의 필드 업데이트
        replayVideo.setVideoTitle(replayVideoDetails.getVideoTitle());
        replayVideo.setVideoUrl(replayVideoDetails.getVideoUrl());
        replayVideo.setVideoImgUrl(replayVideoDetails.getVideoImgUrl());
        replayVideo.setVideoViewers(replayVideoDetails.getVideoViewers());

        // ReplayVideo 특화된 필드 업데이트
        replayVideo.setReplayVideoTextUrl(replayVideoDetails.getReplayVideoTextUrl());

        // 추가 필드 업데이트...

        return replayVideoRepository.save(replayVideo);
    }
}

