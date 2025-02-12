package yeo.nuel.lix.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yeo.nuel.lix.controller.YeonuelixApiResponse;
import yeo.nuel.lix.controller.user.request.UserRegisterRequest;
import yeo.nuel.lix.user.RegisterUserUseCase;
import yeo.nuel.lix.user.command.UserRegistrationCommand;
import yeo.nuel.lix.user.response.UserRegistrationResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/v1/user/register")
    public YeonuelixApiResponse<UserRegistrationResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegistrationResponse register = registerUserUseCase.register(
                UserRegistrationCommand.builder()
                                        .username(request.getUsername())
                                        .encryptedPassword(passwordEncoder.encode(request.getPassword()))
                                        .email(request.getEmail())
                                        .phone(request.getPhone())
                                        .build()
        );

        return YeonuelixApiResponse.ok(register);
    }

}
