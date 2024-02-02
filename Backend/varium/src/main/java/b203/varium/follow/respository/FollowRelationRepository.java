package b203.varium.follow.respository;

import b203.varium.follow.entity.FollowRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRelationRepository extends JpaRepository<FollowRelation, Integer> {
    // 필요에 따라 추가적인 쿼리 메소드들을 여기에 정의할 수 있습니다.
    // 예: 특정 사용자를 팔로우하는 모든 관계를 찾는 메소드
}
