package yeo.nuel.lix.repository.token;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yeo.nuel.lix.entity.token.TokenEntity;
import yeo.nuel.lix.token.InsertTokenPort;
import yeo.nuel.lix.token.SearchTokenPort;
import yeo.nuel.lix.token.TokenPortResponse;
import yeo.nuel.lix.token.UpdateTokenPort;

@Repository
@RequiredArgsConstructor
public class TokenRepository implements SearchTokenPort, InsertTokenPort, UpdateTokenPort {

    private final TokenJpaRepository tokenJpaRepository;

    @Override
    @Transactional
    public TokenPortResponse create(String userId, String accessToken, String refreshToken) {
        TokenEntity entity = TokenEntity.newTokenEntity(userId, accessToken, refreshToken);
        tokenJpaRepository.save(entity);
        return new TokenPortResponse(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public TokenPortResponse findByUserId(String userId) {
        return tokenJpaRepository.findByUserId(userId)
                                 .map(request -> new TokenPortResponse(request.getAccessToken(), request.getRefreshToken()))
                                 .orElseThrow();
    }

    @Override
    @Transactional
    public void updateToken(String userId, String accessToken, String refreshToken) {
        Optional<TokenEntity> byUserId = tokenJpaRepository.findByUserId(userId);
        if (byUserId.isEmpty()) {
            throw new RuntimeException();
        }

        TokenEntity tokenEntity = byUserId.get();
        tokenEntity.updateToken(accessToken, refreshToken);
        tokenJpaRepository.save(tokenEntity);
    }
}
