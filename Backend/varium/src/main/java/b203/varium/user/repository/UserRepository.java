package b203.varium.user.repository;

import b203.varium.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserId(String userId);

    UserEntity findByUserId(String userId);

    UserEntity findByUsername(String username);
}