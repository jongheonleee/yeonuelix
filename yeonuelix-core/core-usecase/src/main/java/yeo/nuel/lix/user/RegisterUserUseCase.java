package yeo.nuel.lix.user;

import yeo.nuel.lix.user.command.UserRegistrationCommand;
import yeo.nuel.lix.user.response.UserRegistrationResponse;

public interface RegisterUserUseCase {
    UserRegistrationResponse register(UserRegistrationCommand command);
    UserRegistrationResponse registerSocialUser(String username, String provider, String providerId);
}
