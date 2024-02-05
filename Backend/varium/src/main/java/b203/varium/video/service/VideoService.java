// VideoService.java
package b203.varium.video.service;

import b203.varium.video.entity.Video;
import b203.varium.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    public Optional<Video> getVideoById(Integer id) {
        return videoRepository.findById(id);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public void deleteVideo(Integer id) {
        videoRepository.deleteById(id);
    }

    // 필요한 경우, VideoCustomRepository의 메소드를 사용하여 추가적인 비즈니스 로직 구현
}
