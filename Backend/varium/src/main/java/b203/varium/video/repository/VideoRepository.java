// Spring Data JPA 사용
package b203.varium.video.repository;

import b203.varium.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    // Spring Data JPA 기본 CRUD 메소드들이 자동으로 제공됩니다.
    // 필요한 경우, 상속 관계에 있는 하위 엔티티에 대한 쿼리 메소드도 여기에 추가할 수 있습니다.
}
