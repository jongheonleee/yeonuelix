package yeo.nuel.lix.token;

public interface InsertTokenPort {
    TokenPortResponse create(String userId, String accessToken, String refreshToken);
}
