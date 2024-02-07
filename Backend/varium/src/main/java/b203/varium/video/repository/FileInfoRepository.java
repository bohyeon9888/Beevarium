package b203.varium.video.repository;

import b203.varium.video.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileEntity, Integer> {

    FileEntity findByVideo_Id(int id);

}
