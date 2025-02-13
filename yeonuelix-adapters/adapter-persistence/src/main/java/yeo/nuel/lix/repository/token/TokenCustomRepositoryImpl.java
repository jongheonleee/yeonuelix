package yeo.nuel.lix.repository.token;

import static yeo.nuel.lix.entity.token.QTokenEntity.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yeo.nuel.lix.entity.token.QTokenEntity;
import yeo.nuel.lix.entity.token.TokenEntity;

@Repository
@RequiredArgsConstructor
public class TokenCustomRepositoryImpl implements TokenCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<TokenEntity> findByUserId(String userId) {
        return jpaQueryFactory.selectFrom(tokenEntity)
                              .where(tokenEntity.userId.eq(userId))
                              .fetch()
                              .stream()
                              .findFirst();
    }
}
