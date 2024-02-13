package b203.varium.relation.service;

import b203.varium.broadcastStation.entity.BroadcastStation;
import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.relation.domain.PointRelationEntity;
import b203.varium.relation.dto.response.RelationDtoResponse;
import b203.varium.relation.exception.BroadcastStationMissingException;
import b203.varium.relation.exception.UserMissingException;
import b203.varium.relation.repository.IRelationRepository;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RelationService {

    private final IRelationRepository relationRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> saveRelation(RelationDtoResponse relationDtoResponse) {
        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        PointRelationEntity pointRelationEntity = new PointRelationEntity();

        BroadcastStation broadcastStation = broadcastStationRepository.findById(relationDtoResponse.getBroadcastStationNo()).orElseThrow(() -> new BroadcastStationMissingException("방송국을 찾을 수 없습니다."));
        pointRelationEntity.setBroadcastStation(broadcastStation);

        UserEntity userEntity = userRepository.findById(relationDtoResponse.getSender()).orElseThrow(() -> new UserMissingException("사용자를 찾을 수 없습니다."));
        pointRelationEntity.setUser(userEntity);

        pointRelationEntity.setPointRelationPoint(relationDtoResponse.getPrice());
        pointRelationEntity.setCreatedDate(nowT);
        pointRelationEntity.setUpdatedDate(nowT);
        return relationRepository.saveRelation(pointRelationEntity);
    }
}
