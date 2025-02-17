package yeo.nuel.lix.movie;

import java.util.List;
import yeo.nuel.lix.movie.response.MovieResponse;

public interface InsertMovieUseCase {
    void insert(List<MovieResponse> items);
}
