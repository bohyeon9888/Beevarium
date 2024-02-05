package b203.varium.broadcasting.repository;

import b203.varium.broadcasting.entity.Broadcasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BroadcastingRepository extends JpaRepository<Broadcasting, Integer> {

    Broadcasting findByBroadcastStation_Id(Integer broadcastStationNo);

    List<Broadcasting> findByBroadcastingTitleContaining(String title);


}
