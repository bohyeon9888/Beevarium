package b203.varium.broadcasting.repository;

import b203.varium.broadcasting.entity.Broadcasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastingRepository extends JpaRepository<Broadcasting, Integer> {
    Boolean existsByBroadcastStation_Id(int id);

    Broadcasting findByBroadcastStation_Id(Integer broadcastStationNo);

    Broadcasting findById(int id);
}
