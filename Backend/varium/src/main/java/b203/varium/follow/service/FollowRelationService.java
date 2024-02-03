package b203.varium.follow.service;

import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.follow.dto.FollowDTO;
import b203.varium.follow.entity.FollowRelation;
import b203.varium.follow.respository.FollowRelationRepository;
import b203.varium.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class FollowRelationService {

    private final FollowRelationRepository followRelationRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final UserRepository userRepository;

    public FollowRelationService(FollowRelationRepository followRelationRepository, BroadcastStationRepository broadcastStationRepository, UserRepository userRepository) {
        this.followRelationRepository = followRelationRepository;
        this.broadcastStationRepository = broadcastStationRepository;
        this.userRepository = userRepository;
    }

    // 새로운 FollowRelation 엔티티를 저장하거나 업데이트
    @Transactional
    public Map<String, String> saveFollowRelation(FollowDTO followDTO) {

        Map<String, String> resp = new HashMap<>();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        FollowRelation relation = new FollowRelation();
        relation.setBroadcastStation(broadcastStationRepository.findById(followDTO.getStationId()));
        relation.setFollower(userRepository.findByUsername(followDTO.getUsername()));
        relation.setCreatedDate(nowT);
        relation.setUpdatedDate(nowT);

        followRelationRepository.save(relation);
        log.debug("success saving follow relation");

        resp.put("status", "success");
        resp.put("msg", "Join Success");
        return resp;
    }

    // 특정 ID를 가진 FollowRelation 엔티티를 검색
//    @Transactional(readOnly = true)
//    public Optional<FollowRelation> getFollowRelationById(Integer id) {
//        return followRelationRepository.findById(id);
//    }

    // 모든 FollowRelation 엔티티를 조회
//    public List<FollowRelation> getAllFollowRelations() {
//        return followRelationRepository.findAll();
//    }

    //특정 ID를 가진 FollowRelation 엔티티를 삭제
//    public void deleteFollowRelation(Integer id) {
//        followRelationRepository.deleteById(id);
//    }

    // 추가적인 비즈니스 로직 메소드들을 여기에 구현할 수 있습니다.
    // 예: 특정 사용자를 팔로우하는 모든 사용자를 찾는 메소드
}
