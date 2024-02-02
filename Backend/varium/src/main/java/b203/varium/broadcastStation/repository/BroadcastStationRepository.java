package b203.varium.broadcastStation.repository;


import b203.varium.broadcastStation.entity.BroadcastStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BroadcastStationRepository extends JpaRepository<BroadcastStation, Integer> {
    // 추가적인 쿼리 메서드 정의 가능
    BroadcastStation findById(int id);

    List<BroadcastStation> findAllByUser_Id(int id);
}
