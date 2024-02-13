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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RelationService {

    private final IRelationRepository relationRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Map<String, Object> saveRelation(RelationDtoResponse relationDtoResponse, String name) {
        Map<String, Object> result = new HashMap<>();

        Timestamp nowT = new Timestamp(System.currentTimeMillis());
        PointRelationEntity pointRelationEntity = new PointRelationEntity();
        BroadcastStation broadcastStation = Optional.ofNullable(broadcastStationRepository.findByUser_UserId(relationDtoResponse.getUserId())).orElseThrow(() -> new BroadcastStationMissingException("방송국을 찾을 수 없습니다."));
        pointRelationEntity.setBroadcastStation(broadcastStation);

        UserEntity userEntity = Optional.ofNullable(userRepository.findByUsername(name)).orElseThrow(() -> new UserMissingException("사용자를 찾을 수 없습니다."));
        pointRelationEntity.setUser(userEntity);

        pointRelationEntity.setPointRelationPoint(relationDtoResponse.getPrice());
        pointRelationEntity.setCreatedDate(nowT);
        pointRelationEntity.setUpdatedDate(nowT);
        relationRepository.saveRelation(pointRelationEntity);

        relationRepository.savePoint(userRepository.findByUserId(relationDtoResponse.getUserId()), relationDtoResponse.getPrice());
        result.put("status", "success");
        return result;
    }
}
