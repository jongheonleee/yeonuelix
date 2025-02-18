package yeo.nuel.lix.movie.response;

import java.util.List;
import lombok.Getter;

@Getter
public class PageableMoviesResponse {

    private final List<MovieResponse> movieResponses;
    private final int page;
    private final boolean hasNext;

    public PageableMoviesResponse(List<MovieResponse> movieResponses, int page, boolean hasNext) {
        this.movieResponses = movieResponses;
        this.page = page;
        this.hasNext = hasNext;
    }
}
