// Spring Data JPA 사용
package b203.varium.video.repository;

import b203.varium.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    Video findById(int id);
}
