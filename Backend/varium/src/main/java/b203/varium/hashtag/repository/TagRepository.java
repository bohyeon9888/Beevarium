package b203.varium.hashtag.repository;

import b203.varium.hashtag.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    boolean existsByTagText(String text);

    TagEntity findByTagText(String text);
}
