package yeo.nuel.lix.movie;

import yeo.nuel.lix.movie.response.PageableMoviesResponse;

public interface FetchMovieUseCase {

    PageableMoviesResponse fetchFromClient(int page);
    PageableMoviesResponse fetchFromDb(int page);
}
