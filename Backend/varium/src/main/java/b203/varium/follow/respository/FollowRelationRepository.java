package b203.varium.follow.respository;

import b203.varium.follow.entity.FollowRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRelationRepository extends JpaRepository<FollowRelation, Integer> {
    List<FollowRelation> findAllByFollower_Username(String username);

    List<FollowRelation> findAllByBroadcastStation_User_Username(String username);
}
