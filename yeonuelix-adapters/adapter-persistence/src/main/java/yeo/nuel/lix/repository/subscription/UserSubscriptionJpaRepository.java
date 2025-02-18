package yeo.nuel.lix.repository.subscription;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.subscription.UserSubscriptionEntity;

public interface UserSubscriptionJpaRepository extends JpaRepository<UserSubscriptionEntity, String> {

    Optional<UserSubscriptionEntity> findByUserId(String userId);
}
