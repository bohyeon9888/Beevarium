package b203.varium.user.repository;

import b203.varium.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    UserEntity findAllByEmailAndCodeName(String email, String codeName);
}