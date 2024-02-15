package b203.varium.relation.repository;

import b203.varium.relation.domain.PointRelationEntity;
import b203.varium.user.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RelationRepositoryImpl implements IRelationRepository {

    @PersistenceContext
    private EntityManager em;

    public void saveRelation(PointRelationEntity pointRelationEntity) {
        em.persist(pointRelationEntity);
    }

    @Override
    public void savePoint(UserEntity userEntity, Integer price) {
        log.info("userEntitiyId = {}", userEntity.getUserId());
        userEntity.setPoint(userEntity.getPoint() + price);
    }

    @Override
    public void sendPoint(UserEntity userEntity, Integer price) {
        userEntity.setPoint(userEntity.getPoint() - price);
    }

}
