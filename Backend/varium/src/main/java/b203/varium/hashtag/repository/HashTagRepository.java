package b203.varium.hashtag.repository;

import b203.varium.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, String> {

    List<HashTag> findAllByBroadcasting_Id(int id);

}
