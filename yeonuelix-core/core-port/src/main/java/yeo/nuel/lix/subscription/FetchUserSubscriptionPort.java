package yeo.nuel.lix.subscription;

import java.util.Optional;

public interface FetchUserSubscriptionPort {

    Optional<UserSubscription> findByUserId(String userId);
}
