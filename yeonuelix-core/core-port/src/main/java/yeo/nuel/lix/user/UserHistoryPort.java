package yeo.nuel.lix.user;

public interface UserHistoryPort {

    void create(String userId, String userRole, String clientIp, String reqMethod, String reqUrl, String reqHeader, String reqPayload);
}
