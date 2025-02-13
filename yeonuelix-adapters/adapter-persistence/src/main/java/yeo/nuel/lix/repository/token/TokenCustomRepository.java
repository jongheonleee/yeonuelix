package yeo.nuel.lix.repository.token;

import java.util.Optional;
import yeo.nuel.lix.entity.token.TokenEntity;

public interface TokenCustomRepository {
    Optional<TokenEntity> findByUserId(String userId);
}
