package b203.varium.video.repository;

import b203.varium.video.entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<VideoFile, Integer> {

    VideoFile findByVideo_Id(int id);

}
