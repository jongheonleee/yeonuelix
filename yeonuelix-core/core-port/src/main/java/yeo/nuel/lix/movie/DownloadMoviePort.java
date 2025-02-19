package yeo.nuel.lix.movie;

public interface DownloadMoviePort {
    void save(UserMovieDownload domain);
    long downloadCntToday(String userId);
}
