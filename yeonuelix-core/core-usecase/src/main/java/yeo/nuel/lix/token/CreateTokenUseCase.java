package yeo.nuel.lix.token;

import yeo.nuel.lix.token.response.TokenResponse;

public interface CreateTokenUseCase {
    TokenResponse createNewToken(String userId);

}
