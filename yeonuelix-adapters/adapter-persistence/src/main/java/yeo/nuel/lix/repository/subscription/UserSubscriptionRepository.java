package yeo.nuel.lix.repository.subscription;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yeo.nuel.lix.entity.subscription.UserSubscriptionEntity;
import yeo.nuel.lix.subscription.FetchUserSubscriptionPort;
import yeo.nuel.lix.subscription.InsertUserSubscriptionPort;
import yeo.nuel.lix.subscription.UpdateUserSubscriptionPort;
import yeo.nuel.lix.subscription.UserSubscription;

@Repository
@RequiredArgsConstructor
public class UserSubscriptionRepository implements FetchUserSubscriptionPort,
        UpdateUserSubscriptionPort, InsertUserSubscriptionPort {

    private final UserSubscriptionJpaRepository userSubscriptionJpaRepository;

    @Override
    @Transactional
    public Optional<UserSubscription> findByUserId(String userId) {
        return userSubscriptionJpaRepository.findByUserId(userId)
                .map(UserSubscriptionEntity::toDomain);
    }

    @Override
    @Transactional
    public void create(String userId) {
        UserSubscription userSubscription = UserSubscription.newSubscription(userId);
        userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription));
    }

    @Override
    @Transactional
    public void update(UserSubscription userSubscription) {
        userSubscriptionJpaRepository.save(UserSubscriptionEntity.toEntity(userSubscription));
    }
}
