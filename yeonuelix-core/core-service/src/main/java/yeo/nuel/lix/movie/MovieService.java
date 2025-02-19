package yeo.nuel.lix.movie;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.movie.response.MovieResponse;
import yeo.nuel.lix.movie.response.PageableMoviesResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements FetchMovieUseCase, InsertMovieUseCase, DownloadMovieUseCase, LikeMovieUseCase {

    private final TmdbMoviePort tmdbMoviePort;
    private final PersistenceMoviePort persistenceMoviePort;
    private final DownloadMoviePort downloadMoviePort;
    private final LikeMoviePort likeMoviePort;
    private final List<UserDownloadMovieRoleValidator> validators;



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

    @Override
    public String download(String userId, String role, String movieId) {
        long cnt = downloadMoviePort.downloadCntToday(userId);
        System.out.println("role = " + role);

        boolean validate = validators.stream()
                .filter(validator -> validator.isTarget(role))
                .findFirst()
                .orElseThrow()
                .validate(cnt);

        if (!validate) {
            throw new RuntimeException("더 이상 다운로드 할 수 없습니다.");
        }

        YeonuelixMovie by = persistenceMoviePort.findBy(movieId);
        downloadMoviePort.save(UserMovieDownload.newDownload(userId, movieId));

        return by.getMovieName();
    }

    @Override
    public void like(String userId, String movieId) {
        Optional<UserMovieLike> byUserIdAndMovieId = likeMoviePort.findByUserIdAndMovieId(userId, movieId);
        if (byUserIdAndMovieId.isEmpty()) {
            likeMoviePort.save(UserMovieLike.newLike(userId, movieId));
        }

        UserMovieLike userMovieLike = byUserIdAndMovieId.get();
        userMovieLike.like();
        likeMoviePort.save(userMovieLike);
    }
}
