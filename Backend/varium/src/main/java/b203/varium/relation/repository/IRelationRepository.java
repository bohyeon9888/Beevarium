package b203.varium.relation.repository;

import b203.varium.relation.domain.PointRelationEntity;
import org.springframework.http.ResponseEntity;

public interface IRelationRepository {

    ResponseEntity<String> saveRelation(PointRelationEntity pointRelationEntity);
}
