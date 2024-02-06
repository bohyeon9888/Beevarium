package b203.varium.broadcastStation.repository;


import b203.varium.broadcastStation.entity.BroadcastStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BroadcastStationRepository extends JpaRepository<BroadcastStation, Integer> {
    // 추가적인 쿼리 메서드 정의 가능
    BroadcastStation findById(int id);

    BroadcastStation findByUser_Username(String username);

    BroadcastStation findByUser_UserId(String userid);
}
