// ClipVideoService.java
package b203.varium.video.service;

import b203.varium.video.entity.ClipVideo;
import b203.varium.video.repository.ClipVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClipVideoService {

    private final ClipVideoRepository clipVideoRepository;

    @Autowired
    public ClipVideoService(ClipVideoRepository clipVideoRepository) {
        this.clipVideoRepository = clipVideoRepository;
    }

    //저장
    public ClipVideo saveClipVideo(ClipVideo clipVideo) {
        return clipVideoRepository.save(clipVideo);
    }

    // 주어진 ID(clipVideoNo)에 해당하는 ClipVideo 엔티티 조회
    public ClipVideo getClipVideoById(Integer clipVideoNo) {
        return clipVideoRepository.findById(clipVideoNo)
                .orElseThrow(() -> new IllegalArgumentException("ClipVideo not found with id: " + clipVideoNo));
    }

    // 예외처리방식 ...optional방식으로 할 수도 있음
//    public Optional<ClipVideo> getClipVideoById(Integer clipVideoNo) {
//        return clipVideoRepository.findById(clipVideoNo);
//    }


    // 데이터베이스에 저장된 모든 ClipVideo 엔티티 인스턴스 조회
    public List<ClipVideo> getAllClipVideos() {
        return clipVideoRepository.findAll();
    }

    //삭제
    public void deleteClipVideo(Integer id) {
        clipVideoRepository.deleteById(id);
    }

    // 방송국 번호에 해당하는 모든 클립 비디오 조회
    public List<ClipVideo> findAllByBroadcastStationNo(Integer broadcastStationNo) {
        // 이 메소드는 ClipVideo 엔티티의 관계와 ClipVideoRepository의 구현에 따라 달라집니다.
        // 예를 들어, broadcastStationNo 필드를 기반으로 검색하는 쿼리 메소드를 ClipVideoRepository에 추가해야 할 수 있습니다.
        return clipVideoRepository.findAllByBroadcastStation_Id(broadcastStationNo);
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

        // ClipVideo 특화된 필드 업데이트 (사용자 지정 필드가 있다면)
        // 예: clipVideo.setSomeClipVideoSpecificField(clipVideoDetails.getSomeClipVideoSpecificField());

        return clipVideoRepository.save(clipVideo);
    }


    // 필요한 경우, ClipVideoCustomRepository의 메소드를 사용하여 추가적인 비즈니스 로직 구현
}