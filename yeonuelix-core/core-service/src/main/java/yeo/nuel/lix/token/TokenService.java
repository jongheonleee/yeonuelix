package yeo.nuel.lix.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import yeo.nuel.lix.token.FetchTokenUseCase;
import yeo.nuel.lix.token.CreateTokenUseCase;
import yeo.nuel.lix.token.UpdateTokenUseCase;
import yeo.nuel.lix.token.response.TokenResponse;
import yeo.nuel.lix.user.FetchUserUseCase;
import yeo.nuel.lix.user.response.UserResponse;

@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {

    @Value("${jwt.secret}")
    private String secretKey;

    private final InsertTokenPort insertTokenPort;
    private final UpdateTokenPort updateTokenPort;
    private final SearchTokenPort searchTokenPort;
    private final FetchUserUseCase fetchUserUseCase;
    private final KakaoTokenPort kakaoTokenPort;

    @Override
    public TokenResponse createNewToken(String userId) {
        String accessToken = getToken(userId, Duration.ofHours(3));
        String refreshToken = getToken(userId, Duration.ofHours(24));

        TokenPortResponse tokenPortResponse = insertTokenPort.create(userId, accessToken,
                refreshToken);

        return TokenResponse.builder()
                .accessToken(tokenPortResponse.getAccessToken())
                .refreshToken(tokenPortResponse.getRefreshToken())
                .build();
    }

    @Override
    public Boolean validateToken(String accessToken) {
        Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJwt(accessToken);

        return true;
    }

    @Override
    public String getTokenFromKakao(String code) {
        return kakaoTokenPort.getAccessTokenByCode(code);
    }

    @Override
    public UserResponse findUserByAccessToken(String accessToken) {
        Claims claims = parseClaims(accessToken);

        Object userId = claims.get("userId");

        if (ObjectUtils.isEmpty(userId)) {
            throw new RuntimeException();
        }


        return fetchUserUseCase.findByProviderId(userId.toString());
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private String getToken(String userId, Duration duration) {
        Date now = new Date();
        Instant instant = now.toInstant();

        return Jwts.builder()
                .claim("userId", userId)
                .issuedAt(now)
                .expiration(Date.from(instant.plus(duration)))
                .signWith(getSignKey())
                .compact();
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
