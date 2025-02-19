package yeo.nuel.lix.batch;

import java.util.LinkedList;
import java.util.List;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import yeo.nuel.lix.movie.FetchMovieUseCase;
import yeo.nuel.lix.movie.YeonuelixMovie;
import yeo.nuel.lix.movie.response.MovieResponse;
import yeo.nuel.lix.movie.response.PageableMoviesResponse;

public class HttpPageItemReader extends AbstractItemCountingItemStreamItemReader<MovieResponse> {

    private int page;
    private final List<MovieResponse> contents = new LinkedList<>();
    private final FetchMovieUseCase fetchMovieUseCase;

    public HttpPageItemReader(int page, FetchMovieUseCase fetchMovieUseCase) {
        this.page = page;
        this.fetchMovieUseCase = fetchMovieUseCase;
    }

    @Override
    protected MovieResponse doRead() throws Exception {
        if (this.contents.isEmpty()) {
            readRow();
        }

        int size = contents.size();
        int index = size - 1;

        if (index < 0) {
            return null;
        }

        return contents.remove(contents.size() - 1);
    }

    @Override
    protected void doOpen() throws Exception {
        setName(HttpPageItemReader.class.getName());
    }

    @Override
    protected void doClose() throws Exception {

    }

    private void readRow() {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        contents.addAll(pageableMoviesResponse.getMovieResponses());
        page++;
    }
}
