package yeo.nuel.lix.movie;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.movie.response.MovieResponse;
import yeo.nuel.lix.movie.response.PageableMoviesResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase {

    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;

    @Override
    public PageableMoviesResponse fetchFromClient(int page) {
        TmdbPageableMovies tmdbPageableMovies = tmdbMoviePort.fetchPageable(page);
        return new PageableMoviesResponse(
                tmdbPageableMovies.getTmdbMovies().stream()
                        .map(movie -> new MovieResponse(
                                movie.getMoviewName(),
                                movie.isAdult(),
                                movie.getGenre(),
                                movie.getOverview(),
                                movie.getReleaseAt()
                        )).collect(Collectors.toList()),
                tmdbPageableMovies.getPage(),
                tmdbPageableMovies.isHasNext()
        );
    }

    @Override
    public void insert(List<MovieResponse> items) {
        items.forEach(it -> {
            YeonuelixMovie yeonuelixMovie = YeonuelixMovie.builder()
                                                          .movieName(it.getMovieName())
                                                          .isAdult(it.isAdult())
                                                          .genre("")
                                                          .overview(it.getOverview())
                                                          .build();
            persistenceMoviePort.insert(yeonuelixMovie);
        });

    }

    @Override
    public PageableMoviesResponse fetchFromDb(int page) {
        List<YeonuelixMovie> yeonuelixMovies = persistenceMoviePort.fetchBy(page, 10);
        return new PageableMoviesResponse(
                yeonuelixMovies.stream()
                               .map(it -> new MovieResponse(
                                                it.getMovieName(),
                                                it.getIsAdult(),
                                                List.of(),
                                                it.getOverview(),
                                                it.getReleasedAt()))
                               .toList(),
                page,
                true
        );
    }
}
