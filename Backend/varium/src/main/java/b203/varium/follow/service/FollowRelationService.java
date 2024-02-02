package b203.varium.follow.service;

import b203.varium.follow.entity.FollowRelation;
import b203.varium.follow.respository.FollowRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowRelationService {

    private final FollowRelationRepository followRelationRepository;

    @Autowired
    public FollowRelationService(FollowRelationRepository followRelationRepository) {
        this.followRelationRepository = followRelationRepository;
    }

    // 새로운 FollowRelation 엔티티를 저장하거나 업데이트
    public FollowRelation saveFollowRelation(FollowRelation followRelation) {
        return followRelationRepository.save(followRelation);
    }

    // 특정 ID를 가진 FollowRelation 엔티티를 검색
    public Optional<FollowRelation> getFollowRelationById(Integer id) {
        return followRelationRepository.findById(id);
    }

    // 모든 FollowRelation 엔티티를 조회
    public List<FollowRelation> getAllFollowRelations() {
        return followRelationRepository.findAll();
    }

    //특정 ID를 가진 FollowRelation 엔티티를 삭제
    public void deleteFollowRelation(Integer id) {
        followRelationRepository.deleteById(id);
    }

    // 추가적인 비즈니스 로직 메소드들을 여기에 구현할 수 있습니다.
    // 예: 특정 사용자를 팔로우하는 모든 사용자를 찾는 메소드
}
