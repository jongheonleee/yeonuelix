package yeo.nuel.lix.user;

import yeo.nuel.lix.user.response.UserResponse;

public interface FetchUserUseCase {

    UserResponse findUserByEmail(String email);
}
