package b203.varium.relation.repository;

import b203.varium.relation.domain.PointRelationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RelationRepositoryImpl implements IRelationRepository {

    @PersistenceContext
    private EntityManager em;

    public ResponseEntity<String> saveRelation(PointRelationEntity pointRelationEntity) {
        em.persist(pointRelationEntity);
        return new ResponseEntity<>("저장 성공", HttpStatus.OK);
    }
}
