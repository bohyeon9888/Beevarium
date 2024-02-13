package b203.varium.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoFileRepository extends JpaRepository<VideoFile, Integer> {

    VideoFile findByVideo_Id(int id);

}
