// Spring Data JPA 사용
package b203.varium.video.repository;

import b203.varium.video.entity.ClipVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClipVideoRepository extends JpaRepository<ClipVideo, Integer> {

    //해당 방송국 번호를 기반으로 클립 비디오를 검색하는 쿼리 메소드
    List<ClipVideo> findAllByBroadcastStation_Id(Integer id);

    @Query("SELECT COALESCE(MAX(cv.clipVideoNo), 0) FROM ClipVideo cv")
    int findMaxClipVideoNo();
    // Spring Data JPA 기본 CRUD 메소드들이 자동으로 제공됩니다.
}
