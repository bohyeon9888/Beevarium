package b203.varium.relation.repository;

import b203.varium.relation.domain.PointRelationEntity;
import b203.varium.user.entity.UserEntity;

public interface IRelationRepository {

    void saveRelation(PointRelationEntity pointRelationEntity);


    void savePoint(UserEntity userEntity, Integer price);

    void sendPoint(UserEntity userEntity, Integer price);
}
