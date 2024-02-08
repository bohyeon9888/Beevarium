// ClipVideoService.java
package b203.varium.video.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import b203.varium.video.dto.ClipVideoDTO;
import b203.varium.video.dto.FileInfoDTO;
import b203.varium.video.entity.ClipVideo;
import b203.varium.video.entity.VideoFile;
import b203.varium.video.repository.ClipVideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClipVideoService {

    private final ClipVideoRepository clipVideoRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final UserRepository userRepository;

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
            VideoFile file = clip.getFile();
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

    public void saveClipVideo(String username, String streamerId, String title, MultipartFile video, MultipartFile imgFile) {
        BroadcastStation station = broadcastStationRepository.findByUser_UserId(streamerId);
        UserEntity writer = userRepository.findByUsername(username);

        if (station == null || writer == null) {
            return;
        }

        ClipVideo clip = new ClipVideo();
        clip.setClipVideoNo(clipVideoRepository.findMaxClipVideoNo() + 1);
        clip.setUser(writer);
        clip.setVideoTitle(title);
        clip.setVideoImgUrl("none");
    }

    public ClipVideo getClipVideoById(Integer clipVideoNo) {
        return clipVideoRepository.findById(clipVideoNo)
                .orElseThrow(() -> new IllegalArgumentException("ClipVideo not found with id: " + clipVideoNo));
    }

    //삭제
    public void deleteClipVideo(Integer id) {
        clipVideoRepository.deleteById(id);
    }


}