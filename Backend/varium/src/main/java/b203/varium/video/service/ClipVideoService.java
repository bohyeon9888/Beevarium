// ClipVideoService.java
package b203.varium.video.service;

import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.video.dto.ClipVideoDTO;
import b203.varium.video.dto.FileInfoDTO;
import b203.varium.video.entity.ClipVideo;
import b203.varium.video.entity.FileEntity;
import b203.varium.video.repository.ClipVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClipVideoService {

    private final ClipVideoRepository clipVideoRepository;
    private final BroadcastStationRepository broadcastStationRepository;

    public List<ClipVideoDTO> getClipVideos(String streamerid) {
        int stationId = broadcastStationRepository.findByUser_UserId(streamerid).getId();
        List<ClipVideo> list = clipVideoRepository.findAllByBroadcastStation_Id(stationId);
        List<ClipVideoDTO> result = new ArrayList<>();

        for (ClipVideo clip : list) {
            ClipVideoDTO videoDTO = new ClipVideoDTO();
            videoDTO.setId(clip.getId());
            videoDTO.setStationId(stationId);
            videoDTO.setTitle(clip.getVideoTitle());
            videoDTO.setViewers(clip.getVideoViewers());
            videoDTO.setImgUrl(clip.getVideoImgUrl());
            videoDTO.setCreatedDate(clip.getCreatedDate());

            // video service에 따로 빼자
            FileEntity file = clip.getFile();
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

    public ClipVideo saveClipVideo(ClipVideo clipVideo) {
        return clipVideoRepository.save(clipVideo);
    }

    // 주어진 ID(clipVideoNo)에 해당하는 ClipVideo 엔티티 조회
    public ClipVideo getClipVideoById(Integer clipVideoNo) {
        return clipVideoRepository.findById(clipVideoNo)
                .orElseThrow(() -> new IllegalArgumentException("ClipVideo not found with id: " + clipVideoNo));
    }

    //삭제
    public void deleteClipVideo(Integer id) {
        clipVideoRepository.deleteById(id);
    }


    // 수정
    public ClipVideo updateClipVideo(Integer clipVideoNo, ClipVideo clipVideoDetails) {
        ClipVideo clipVideo = clipVideoRepository.findById(clipVideoNo)
                .orElseThrow(() -> new IllegalArgumentException("ClipVideo not found with id: " + clipVideoNo));

        // Video 클래스의 필드 업데이트
        clipVideo.setVideoTitle(clipVideoDetails.getVideoTitle());
        clipVideo.setVideoUrl(clipVideoDetails.getVideoUrl());
        clipVideo.setVideoImgUrl(clipVideoDetails.getVideoImgUrl());
        clipVideo.setVideoViewers(clipVideoDetails.getVideoViewers());


        return clipVideoRepository.save(clipVideo);
    }


}