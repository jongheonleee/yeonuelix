package yeo.nuel.lix.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.movie.UserMovieDownloadEntity;

public interface UserMovieDownloadJpaRepository extends JpaRepository<UserMovieDownloadEntity, String>, UserMovieDownloadCustomRepository {

}
