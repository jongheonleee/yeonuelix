package yeo.nuel.lix.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPortResponse {

    private String userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String provider;
    private String providerId;
    private String role;
}
