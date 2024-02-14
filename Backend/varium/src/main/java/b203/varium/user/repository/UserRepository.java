package b203.varium.user.repository;

import b203.varium.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserId(String userId);

    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    UserEntity findByUsername(String username);

    UserEntity findAllByEmailAndCodeName(String email, String codeName);

    List<UserEntity> findAllByUsernameContaining(String keyword);
}