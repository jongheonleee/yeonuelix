package yeo.nuel.lix.user;

public interface InsertUserPort {
    UserPortResponse create(CreateUser user);
    UserPortResponse createSocialUser(String username, String provider, String providerId);
}
