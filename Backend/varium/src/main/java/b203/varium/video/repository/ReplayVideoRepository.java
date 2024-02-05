package b203.varium.video.repository;

import b203.varium.video.entity.ReplayVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplayVideoRepository extends JpaRepository<ReplayVideo, Integer> {
    // BroadcastStation의 ID를 기준으로 ReplayVideo 조회
    List<ReplayVideo> findAllByBroadcastStation_Id(Integer id);

}
