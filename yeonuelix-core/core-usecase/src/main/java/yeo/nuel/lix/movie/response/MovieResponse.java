package yeo.nuel.lix.movie.response;

import java.util.List;
import lombok.Getter;

@Getter
public class MovieResponse {

    private final String moviewName;
    private final boolean isAdult;
    private final List<String> genre;
    private final String overview;
    private final String releaseAt;

    public MovieResponse(String moviewName, boolean isAdult, List<String> genre, String overview, String releaseAt) {
        this.moviewName = moviewName;
        this.isAdult = isAdult;
        this.genre = genre;
        this.overview = overview;
        this.releaseAt = releaseAt;
    }
}
