package yeo.nuel.lix.token;

public interface SearchTokenPort {
    TokenPortResponse findByUserId(String userId);
}
