package yeo.nuel.lix.repository.movie;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yeo.nuel.lix.entity.movie.UserMovieLikeEntity;
import yeo.nuel.lix.movie.LikeMoviePort;
import yeo.nuel.lix.movie.UserMovieLike;


@Repository
@RequiredArgsConstructor
public class UserMovieLikeRepository implements LikeMoviePort {

    private final UserMovieLikeJpaRepository userMovieLikeJpaRepository;

    @Override
    public void save(UserMovieLike domain) {
        userMovieLikeJpaRepository.save(UserMovieLikeEntity.toEntity(domain));
    }

    @Override
    public Optional<UserMovieLike> findByUserIdAndMovieId(String userId, String movieId) {
        return userMovieLikeJpaRepository.findByUserIdAndMovieId(userId, movieId)
                                        .map(UserMovieLikeEntity::toDomain);
    }
}
