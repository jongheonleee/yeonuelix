package yeo.nuel.lix.user;

public interface LogUserAuditHistoryUseCase {

    void log(String userId, String userRole, String clientIp, String reqMethod, String reqUrl, String reqHeader, String reqPayload);
}
