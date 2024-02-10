package b203.varium.broadcasting.repository;

import b203.varium.broadcasting.entity.Broadcasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadcastingRepository extends JpaRepository<Broadcasting, Integer> {
    Boolean existsByBroadcastStation_Id(int id);

    Broadcasting findByBroadcastStation_Id(Integer broadcastStationNo);

    Broadcasting findById(int id);

    List<Broadcasting> findTop8ByOrderByBroadcastingViewersDesc();
}
