package b203.varium.hashtag.repository;

import b203.varium.hashtag.entity.TagEntity;
import b203.varium.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    boolean existsByTagText(String text);

    TagEntity findByTagText(String text);

    TagEntity findById(int id);

    List<TagEntity> findAllByTagTextContaining(String keyword);
}
