package yeo.nuel.lix.repository.movie;

public interface UserMovieDownloadCustomRepository {
    long countDownloadToday(String userId);
}
