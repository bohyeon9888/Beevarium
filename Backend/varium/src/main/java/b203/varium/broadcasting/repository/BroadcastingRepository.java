package b203.varium.broadcasting.repository;

import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadcastingRepository extends JpaRepository<Broadcasting, Integer> {
    Boolean existsByBroadcastStation_Id(int id);

    Broadcasting findByBroadcastStation_Id(Integer broadcastStationNo);

    Broadcasting findById(int id);

    Broadcasting findByBroadcastStation_User_Id(int userId);

    List<Broadcasting> findTop8ByOrderByBroadcastingViewersDesc();

}
