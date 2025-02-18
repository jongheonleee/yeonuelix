package yeo.nuel.lix.controller.movie;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yeo.nuel.lix.controller.YeonuelixApiResponse;
import yeo.nuel.lix.movie.FetchMovieUseCase;
import yeo.nuel.lix.movie.response.MovieResponse;
import yeo.nuel.lix.movie.response.PageableMoviesResponse;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final FetchMovieUseCase fetchMovieUseCase;

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
}
