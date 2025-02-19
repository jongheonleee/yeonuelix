package yeo.nuel.lix.repository.movie;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.movie.UserMovieLikeEntity;

public interface UserMovieLikeJpaRepository extends JpaRepository<UserMovieLikeEntity, String> {
    Optional<UserMovieLikeEntity> findByUserIdAndMovieId(String userId, String movieId);
}
