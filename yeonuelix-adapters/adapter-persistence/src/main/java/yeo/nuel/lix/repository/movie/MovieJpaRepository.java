package yeo.nuel.lix.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import yeo.nuel.lix.entity.movie.MovieEntity;

public interface MovieJpaRepository extends JpaRepository<MovieEntity, String>, MovieCustomRepository {

}
