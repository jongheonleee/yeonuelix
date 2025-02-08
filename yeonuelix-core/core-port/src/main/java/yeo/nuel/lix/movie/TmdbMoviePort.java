package yeo.nuel.lix.movie;

public interface TmdbMoviePort {

    TmdbPageableMovies fetchPageable(int page);
}
