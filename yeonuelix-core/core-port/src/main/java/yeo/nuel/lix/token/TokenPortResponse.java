package yeo.nuel.lix.token;

import lombok.Getter;

@Getter
public class TokenPortResponse {

    private final String accessToken;
    private final String refreshToken;

    public TokenPortResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
