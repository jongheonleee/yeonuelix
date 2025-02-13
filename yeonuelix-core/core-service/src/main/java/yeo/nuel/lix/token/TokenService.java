package yeo.nuel.lix.token;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.token.FetchTokenUseCase;
import yeo.nuel.lix.token.CreateTokenUseCase;
import yeo.nuel.lix.token.UpdateTokenUseCase;
import yeo.nuel.lix.token.response.TokenResponse;
import yeo.nuel.lix.user.response.UserResponse;

@Service
@RequiredArgsConstructor
public class TokenService implements FetchTokenUseCase, CreateTokenUseCase, UpdateTokenUseCase {

    @Override
    public TokenResponse createNewToken(String userId) {
        return null;
    }

    @Override
    public Boolean validateToken(String token) {
        return null;
    }

    @Override
    public String getTokenFromKakao(String code) {
        return "";
    }

    @Override
    public UserResponse findUserByAccessToken(String accessToken) {
        return null;
    }
}
