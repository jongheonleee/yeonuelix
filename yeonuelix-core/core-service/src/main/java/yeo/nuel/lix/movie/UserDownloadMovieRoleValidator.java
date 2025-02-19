package yeo.nuel.lix.movie;


// 전략 패턴
public interface UserDownloadMovieRoleValidator {
    boolean validate(long count);
    boolean isTarget(String role);
}
