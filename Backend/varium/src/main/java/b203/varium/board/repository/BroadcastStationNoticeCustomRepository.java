package b203.varium.board.repository;// 커스텀 쿼리 인터페이스


import b203.varium.board.entity.BroadcastStationNotice;

import java.util.List;

public interface BroadcastStationNoticeCustomRepository {
    List<BroadcastStationNotice> findCustomNoticesByCriteria();

    List<BroadcastStationNotice> findNoticesByStationId(Integer broadcastStationNo);
}
