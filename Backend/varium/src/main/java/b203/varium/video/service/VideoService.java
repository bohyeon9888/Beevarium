// VideoService.java
package b203.varium.video.service;

import b203.varium.video.entity.Video;
import b203.varium.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoService {

    private final VideoRepository videoRepository;

    // 저장
//    public Video saveVideo(Video video) {
//        return videoRepository.save(video);
//    }

    //전체 비디요 조회
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    // 방송국 아이디로 비디오 조회
    public Optional<Video> getVideoById(Integer id) {
        return videoRepository.findById(id);
    }

    public void deleteVideo(Integer id) {
        videoRepository.deleteById(id);
    }

    // 필요한 경우, VideoCustomRepository의 메소드를 사용하여 추가적인 비즈니스 로직 구현
}
