package yeo.nuel.lix.repository.movie;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yeo.nuel.lix.entity.movie.MovieEntity;
import yeo.nuel.lix.movie.PersistenceMoviePort;
import yeo.nuel.lix.movie.YeonuelixMovie;

@Repository
@RequiredArgsConstructor
public class MovieRepository implements PersistenceMoviePort {

    private final MovieJpaRepository movieJpaRepository;

    @Override
    @Transactional
    public List<YeonuelixMovie> fetchBy(int page, int size) {
        return movieJpaRepository.search(PageRequest.of(page, size))
                                 .stream()
                                 .map(MovieEntity::toDomain)
                                 .toList();
    }

    @Override
    @Transactional
    public YeonuelixMovie findBy(String movieName) {
        return movieJpaRepository.findByMovieName(movieName)
                                 .map(MovieEntity::toDomain)
                                 .orElseThrow();
    }

    @Override
    @Transactional
    public void insert(YeonuelixMovie yeonuelixMovie) {
        Optional<MovieEntity> byMovieName = movieJpaRepository.findByMovieName(yeonuelixMovie.getMovieName());

        if (byMovieName.isPresent()) {
            return;
        }

        MovieEntity movieEntity = MovieEntity.newEntity(
                yeonuelixMovie.getMovieName(),
                yeonuelixMovie.getIsAdult(),
                yeonuelixMovie.getGenre(),
                yeonuelixMovie.getOverview(),
                yeonuelixMovie.getReleasedAt()
        );

        movieJpaRepository.save(movieEntity);
    }
}
