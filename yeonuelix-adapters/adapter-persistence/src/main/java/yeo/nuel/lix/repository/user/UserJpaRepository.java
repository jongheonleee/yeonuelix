package yeo.nuel.lix.repository.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.user.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

   Optional<UserEntity> findByEmail(String email);
}
