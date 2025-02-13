package yeo.nuel.lix.token;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class YeonuelixToken {

    private final String accessToken;
    private final String refreshToken;
    private final LocalDateTime accessTokenExpiredAt;
    private final LocalDateTime refreshTokenExpiredAt;

    public YeonuelixToken(String accessToken, String refreshToken, LocalDateTime accessTokenExpiredAt, LocalDateTime refreshTokenExpiredAt) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiredAt = accessTokenExpiredAt;
        this.refreshTokenExpiredAt = refreshTokenExpiredAt;
    }
}
