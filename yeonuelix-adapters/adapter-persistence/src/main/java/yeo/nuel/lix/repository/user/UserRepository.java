package yeo.nuel.lix.repository.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yeo.nuel.lix.entity.user.SocialUserEntity;
import yeo.nuel.lix.entity.user.UserEntity;
import yeo.nuel.lix.repository.subscription.UserSubscriptionRepository;
import yeo.nuel.lix.repository.user.social.SocialUserJpaRepository;
import yeo.nuel.lix.subscription.UserSubscription;
import yeo.nuel.lix.user.CreateUser;
import yeo.nuel.lix.user.FetchUserPort;
import yeo.nuel.lix.user.InsertUserPort;
import yeo.nuel.lix.user.UserPortResponse;

@Repository
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort, InsertUserPort {

    private final UserJpaRepository userJpaRepository;
    private final SocialUserJpaRepository socialUserJpaRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Override
    @Transactional
    public Optional<UserPortResponse> findByEmail(String email) {
        Optional<UserEntity> byEmail = userJpaRepository.findByEmail(email);
        return byEmail.map(userEntity -> UserPortResponse.builder()
                                                        .userId(userEntity.getUserId())
                                                        .username(userEntity.getUsername())
                                                        .password(userEntity.getPassword())
                                                        .email(userEntity.getEmail())
                                                        .phone(userEntity.getPhone())
                                                        .build());
    }

    @Override
    public Optional<UserPortResponse> findByProviderId(String providerId) {
        Optional<SocialUserEntity> byProviderId = socialUserJpaRepository.findByProviderId(
                providerId);

        if (byProviderId.isEmpty()) {
            return Optional.empty();
        }

        SocialUserEntity socialUserEntity = byProviderId.get();

        Optional<UserSubscription> byUserId = userSubscriptionRepository.findByUserId(socialUserEntity.getSocialUserId());



        return Optional.of(
                UserPortResponse.builder()
                        .userId(socialUserEntity.getSocialUserId())
                        .provider(socialUserEntity.getProvider())
                        .providerId(socialUserEntity.getProviderId())
                        .username(socialUserEntity.getUsername())
                        .role(byUserId.orElse(UserSubscription.newSubscription(socialUserEntity.getSocialUserId()))
                                .getSubscriptionType()
                                .toRole())
                        .build());
    }

    @Override
    @Transactional
    public UserPortResponse create(CreateUser user) {
        UserEntity userEntity = new UserEntity(user.getUsername(), user.getEncryptedPassword(), user.getEmail(), user.getPhone());
        UserEntity save = userJpaRepository.save(userEntity);

        userSubscriptionRepository.create(save.getUserId());

        return UserPortResponse.builder()
                .userId(save.getUserId())
                .username(save.getUsername())
                .password(save.getPassword())
                .email(save.getEmail())
                .phone(save.getPhone())
                .build();
    }

    @Override
    @Transactional
    public UserPortResponse createSocialUser(String username, String provider, String providerId) {
        SocialUserEntity socialUserEntity = new SocialUserEntity(username, provider, providerId);
        socialUserJpaRepository.save(socialUserEntity);

        userSubscriptionRepository.create(socialUserEntity.getSocialUserId());

        return UserPortResponse.builder()
                            .provider(provider)
                            .providerId(providerId)
                            .username(username)
                            .build();
    }
}
