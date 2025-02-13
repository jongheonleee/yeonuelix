package yeo.nuel.lix.token;

public interface UpdateTokenPort {
    void updateToken(String userId, String accessToken, String refreshToken);
}
