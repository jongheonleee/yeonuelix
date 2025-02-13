package yeo.nuel.lix.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.token.TokenEntity;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, String>, TokenCustomRepository {

}
