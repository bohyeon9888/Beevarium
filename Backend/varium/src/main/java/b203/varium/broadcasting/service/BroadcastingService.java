package b203.varium.broadcasting.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.broadcasting.entity.Broadcasting;
import b203.varium.broadcasting.repository.BroadcastingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BroadcastingService {

    private final BroadcastingRepository broadcastingRepository;
    private final BroadcastStationRepository broadcastStationRepository;

    public BroadcastingService(BroadcastingRepository broadcastingRepository, BroadcastStationRepository broadcastStationRepository) {
        this.broadcastingRepository = broadcastingRepository;
        this.broadcastStationRepository = broadcastStationRepository;
    }

    @Transactional
    public void startBroadcasting(int stationId, String title, String url, String thumbnail) {
        BroadcastStation station = broadcastStationRepository.findById(stationId);
        Broadcasting data = new Broadcasting(station, title, url, thumbnail);
        broadcastingRepository.save(data);
    }
}
