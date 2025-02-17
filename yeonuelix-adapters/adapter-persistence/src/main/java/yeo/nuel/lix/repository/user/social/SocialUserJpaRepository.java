package yeo.nuel.lix.repository.user.social;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.user.SocialUserEntity;

public interface SocialUserJpaRepository extends JpaRepository<SocialUserEntity, String> {

    Optional<SocialUserEntity> findByProviderId(String providerId);
}
