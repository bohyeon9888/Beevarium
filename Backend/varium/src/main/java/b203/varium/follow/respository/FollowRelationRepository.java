package b203.varium.follow.respository;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.follow.entity.FollowRelation;
import b203.varium.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRelationRepository extends JpaRepository<FollowRelation, Integer> {

    Boolean existsByBroadcastStationAndFollower(BroadcastStation station, UserEntity user);

    List<FollowRelation> findAllByFollower_Username(String username);

    List<FollowRelation> findAllByBroadcastStation_User_Username(String username);

    FollowRelation findByBroadcastStation_IdAndFollower_Username(int stationId, String username);
}
