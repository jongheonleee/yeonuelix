package yeo.nuel.lix.repository.movie;

import static yeo.nuel.lix.entity.movie.QUserMovieDownloadEntity.userMovieDownloadEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadCustomRepositoryImpl implements UserMovieDownloadCustomRepository {

    private final JPAQueryFactory factory;

    @Override
    public long countDownloadToday(String userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime end = now.plusDays(1).truncatedTo(ChronoUnit.DAYS);

        return factory.selectFrom(userMovieDownloadEntity)
                      .where( userMovieDownloadEntity.userId.eq(userId)
                            .and(userMovieDownloadEntity.createdAt.goe(start))
                            .and(userMovieDownloadEntity.createdAt.lt(end)))
                      .fetch()
                      .size();
    }

}
