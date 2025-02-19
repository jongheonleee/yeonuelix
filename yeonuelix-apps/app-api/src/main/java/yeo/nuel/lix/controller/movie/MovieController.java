package yeo.nuel.lix.controller.movie;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yeo.nuel.lix.controller.YeonuelixApiResponse;
import yeo.nuel.lix.filter.JwtTokenProvider;
import yeo.nuel.lix.movie.DownloadMovieUseCase;
import yeo.nuel.lix.movie.FetchMovieUseCase;
import yeo.nuel.lix.movie.LikeMovieUseCase;
import yeo.nuel.lix.movie.response.PageableMoviesResponse;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;
    private final DownloadMovieUseCase downloadMovieUseCase;
    private final LikeMovieUseCase likeMovieUseCase;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping("/api/v1/movie/client/{page}")
    public YeonuelixApiResponse fetchMoviePageables(@PathVariable int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        return YeonuelixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/search")
    public YeonuelixApiResponse<PageableMoviesResponse> search(@RequestParam int page) {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromDb(page);
        return YeonuelixApiResponse.ok(pageableMoviesResponse);
    }

    @PostMapping("/api/v1/movie/{movieId}/download")
    @PreAuthorize("hasAnyRole('ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public YeonuelixApiResponse<String> download(@PathVariable String movieId) {
        String download = downloadMovieUseCase.download(jwtTokenProvider.getUserId(), jwtTokenProvider.getRole(), movieId);
        return YeonuelixApiResponse.ok(download);
    }

    @PostMapping("/api/v1/movie/{movieId}/like")
    @PreAuthorize("hasAnyRole('ROLE_FREE', 'ROLE_BRONZE', 'ROLE_SILVER', 'ROLE_GOLD')")
    public YeonuelixApiResponse<String> like(@PathVariable String movieId) {
        String userId = jwtTokenProvider.getUserId();
        likeMovieUseCase.like(userId, movieId);
        return YeonuelixApiResponse.ok("");
    }
}
