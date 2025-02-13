package yeo.nuel.lix.repository.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        return null;
    }

    @Override
    @Transactional
    public TokenPortResponse findByUserId(String userId) {
        return null;
    }

    @Override
    @Transactional
    public void updateToken(String userId, String accessToken, String refreshToken) {

    }
}
