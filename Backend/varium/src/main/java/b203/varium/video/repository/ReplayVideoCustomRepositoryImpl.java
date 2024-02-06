//package b203.varium.video.repository;
//
//import b203.varium.video.entity.ReplayVideo;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//import java.util.List;
//
//public class ReplayVideoCustomRepositoryImpl implements ReplayVideoCustomRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<ReplayVideo> findAllByBroadcastStationNo(Integer broadcastStationNo) {
//        return em.createQuery("SELECT rv FROM ReplayVideo rv WHERE rv.broadcastStation.id = :broadcastStationNo", ReplayVideo.class)
//                .setParameter("broadcastStationNo", broadcastStationNo)
//                .getResultList();
//    }
//}
