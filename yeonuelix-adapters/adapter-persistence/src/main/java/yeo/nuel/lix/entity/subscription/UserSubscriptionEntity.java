package yeo.nuel.lix.entity.subscription;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yeo.nuel.lix.subscription.SubscriptionType;
import yeo.nuel.lix.subscription.UserSubscription;

@Getter
@Entity
@Table(name = "user_subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserSubscriptionEntity {

    @Id
    @Column(name = "USER_SUBSCRIPTION_ID")
    private String userSubscriptionId;

    @Column(name = "USER_ID")
    private String userId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "SUBSCRIPTION_NAME")
    private SubscriptionType subscriptionType;

    @Column(name = "START_AT")
    private LocalDateTime subscriptionStartAt;

    @Column(name = "END_AT")
    private LocalDateTime subscriptionEndAt;

    @Column(name = "VALID_YN")
    private Boolean validYn;

    public UserSubscription toDomain() {
        return UserSubscription.builder()
                .userId(userId)
                .subscriptionType(subscriptionType)
                .startAt(subscriptionStartAt)
                .endAt(subscriptionEndAt)
                .validYn(validYn)
                .build();
    }

    public static UserSubscriptionEntity toEntity(UserSubscription userSubscription) {
        return new UserSubscriptionEntity(
                UUID.randomUUID().toString(),
                userSubscription.getUserId(),
                userSubscription.getSubscriptionType(),
                userSubscription.getStartAt(),
                userSubscription.getEndAt(),
                userSubscription.getValidYn()
        );

    }
}
