package yeo.nuel.lix.movie;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class YeonuelixMovie {

    private final String movieName;
    private final Boolean isAdult;
    private final String genre;
    private final String overview;
    private final String releasedAt;

    public YeonuelixMovie(String movieName, Boolean isAdult, String genre, String overview, String releasedAt) {
        this.movieName = movieName;
        this.isAdult = isAdult;
        this.genre = genre;
        this.overview = overview;
        this.releasedAt = releasedAt;
    }
}
