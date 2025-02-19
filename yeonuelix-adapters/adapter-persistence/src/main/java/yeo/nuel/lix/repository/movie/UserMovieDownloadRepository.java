package yeo.nuel.lix.repository.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yeo.nuel.lix.entity.movie.UserMovieDownloadEntity;
import yeo.nuel.lix.movie.DownloadMoviePort;
import yeo.nuel.lix.movie.UserMovieDownload;

@Repository
@RequiredArgsConstructor
public class UserMovieDownloadRepository implements DownloadMoviePort {

    private final UserMovieDownloadJpaRepository userMovieDownloadJpaRepository;

    @Override
    @Transactional
    public void save(UserMovieDownload domain) {
        userMovieDownloadJpaRepository.save(UserMovieDownloadEntity.toEntity(domain));
    }

    @Override
    @Transactional
    public long downloadCntToday(String userId) {
        return userMovieDownloadJpaRepository.countDownloadToday(userId);
    }
}
