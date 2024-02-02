//// 커스텀 쿼리 구현
//package com.pjt.beevarium.board.repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//import com.pjt.beevarium.board.entity.BroadcastStationNotice;
//
//public class BroadcastStationNoticeRepositoryImpl implements BroadcastStationNoticeCustomRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<BroadcastStationNotice> findCustomNoticesByCriteria() {
//        // 여기에 순수 JPA를 사용한 쿼리 구현
//        // 예: return em.createQuery("SELECT n FROM BroadcastStationNotice n WHERE ...", BroadcastStationNotice.class).getResultList();
//        return null; // 실제 구현 필요
//    }
//}
