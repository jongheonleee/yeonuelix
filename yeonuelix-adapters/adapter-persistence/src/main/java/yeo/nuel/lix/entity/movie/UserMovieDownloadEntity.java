package yeo.nuel.lix.entity.movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yeo.nuel.lix.audit.MutableBaseEntity;
import yeo.nuel.lix.movie.UserMovieDownload;

@Getter
@Entity
@Table(name = "user_movie_download")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMovieDownloadEntity extends MutableBaseEntity {

    @Id
    @Column(name = "USER_MOVIE_DOWNLOAD_ID")
    private String userMovieDownloadId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "MOVIE_ID")
    private String movieId;

    public UserMovieDownload toDomain() {
        return UserMovieDownload.builder()
                                .userMovieDownloadId(userMovieDownloadId)
                                .userId(userId)
                                .movieId(movieId)
                                .build();
    }

    public static UserMovieDownloadEntity toEntity(UserMovieDownload domain) {
        return new UserMovieDownloadEntity(
                domain.getUserMovieDownloadId(),
                domain.getUserId(),
                domain.getMovieId()
        );
    }
}
