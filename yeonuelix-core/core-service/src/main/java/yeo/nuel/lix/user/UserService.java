package yeo.nuel.lix.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.exception.UserException;
import yeo.nuel.lix.user.command.UserRegistrationCommand;
import yeo.nuel.lix.user.response.UserRegistrationResponse;
import yeo.nuel.lix.user.response.UserResponse;

@Service
@RequiredArgsConstructor
public class UserService implements FetchUserUseCase, RegisterUserUseCase {

    private final FetchUserPort fetchUserPort;
    private final InsertUserPort insertUserPort;

    @Override
    public UserResponse findUserByEmail(String email) {
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new UserException.UserDoesNotExistException();
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

    @Override
    public UserRegistrationResponse register(UserRegistrationCommand command) {
        String email = command.getEmail();

        // 사용자 조회
        Optional<UserPortResponse> byEmail = fetchUserPort.findByEmail(email);

        // 만약 있으면? 예외 던지기
        if (byEmail.isPresent()) {
            throw new UserException.UserAlreadyExistsException();
        }


        // 없으면? 회원가입 시도
        UserPortResponse response = insertUserPort.create(CreateUser.builder()
                        .username(command.getUsername())
                        .encryptedPassword(command.getEncryptedPassword())
                        .email(command.getEmail())
                        .phone(command.getPhone())
                        .build()
        );

        // 리스폰스 반환
        return new UserRegistrationResponse(
                response.getUsername(),
                response.getEmail(),
                response.getPhone()
        );
    }

    @Override
    public UserResponse findByProviderId(String userId) {
        return null;
    }
}
