package yeo.nuel.lix.movie.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PageableMoviesResponse {

    private final List<MovieResponse> movieRespons;
    private final int page;
    private final boolean hasNext;

    public PageableMoviesResponse(List<MovieResponse> movieRespons, int page, boolean hasNext) {
        this.movieRespons = movieRespons;
        this.page = page;
        this.hasNext = hasNext;
    }
}
