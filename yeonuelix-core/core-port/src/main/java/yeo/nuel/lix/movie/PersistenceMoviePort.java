package yeo.nuel.lix.movie;

import java.util.List;

public interface PersistenceMoviePort {

    List<YeonuelixMovie> fetchBy(int page, int size);
    YeonuelixMovie findBy(String movieName);
    void insert(YeonuelixMovie yeonuelixMovie);
}
