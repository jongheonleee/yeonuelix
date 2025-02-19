package yeo.nuel.lix.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.user.UserHistoryEntity;

public interface UserHistoryJpaRepository extends JpaRepository<UserHistoryEntity, Long> {
}
