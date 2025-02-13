package yeo.nuel.lix.token;

import yeo.nuel.lix.user.response.UserResponse;

public interface FetchTokenUseCase {

    Boolean validateToken(String token);
    String getTokenFromKakao(String code);
    UserResponse findUserByAccessToken(String accessToken);
}
