package yeo.nuel.lix.user;

import yeo.nuel.lix.user.command.UserResponse;

public interface FetchUserUseCase {

    UserResponse findUserByEmail(String email);
}
