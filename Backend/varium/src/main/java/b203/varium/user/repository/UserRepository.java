package b203.varium.user.repository;

import b203.varium.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}