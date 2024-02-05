// 커스텀 쿼리 구현
package b203.varium.board.repository;

import b203.varium.board.entity.BroadcastStationNotice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BroadcastStationNoticeRepositoryImpl implements BroadcastStationNoticeCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BroadcastStationNotice> findCustomNoticesByCriteria() {
        // 여기에 순수 JPA를 사용한 쿼리 구현
        // 예: return em.createQuery("SELECT n FROM BroadcastStationNotice n WHERE ...", BroadcastStationNotice.class).getResultList();
        return null; // 실제 구현 필요
    }

    @Override
    public List<BroadcastStationNotice> findNoticesByStationId(Integer broadcastStationNo) {
        return em.createQuery("select b from BroadcastStationNotice b where b.broadcastStationNo = :broadcastStationNo", BroadcastStationNotice.class)
                .setParameter("broadcastStationNo", broadcastStationNo)
                .getResultList();
    }
}
