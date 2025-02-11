package yeo.nuel.lix.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.user.command.UserResponse;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase {


    private final FetchUserPort fetchUserPort;

    @Override
    public UserResponse findUserByEmail(String email) {
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new RuntimeException();
        }

        UserPortResponse userPortResponse = byEmail.get();


        return UserResponse.builder()
                            .userId(userPortResponse.getUserId())
                            .username(userPortResponse.getUsername())
                            .password(userPortResponse.getPassword())
                            .email(userPortResponse.getEmail())
                            .phone(userPortResponse.getPhone())
                            .role(userPortResponse.getRole())
                            .build();
    }

}
