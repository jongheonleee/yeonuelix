package yeo.nuel.lix.repository.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yeo.nuel.lix.entity.user.UserEntity;
import yeo.nuel.lix.user.FetchUserPort;
import yeo.nuel.lix.user.UserPortResponse;

@Repository
@RequiredArgsConstructor
public class UserRepository implements FetchUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserPortResponse> findByEmail(String email) {
        Optional<UserEntity> byEmail = userJpaRepository.findByEmail(email);
        return byEmail.map(userEntity -> UserPortResponse.builder()
                                                        .userId(userEntity.getUserId())
                                                        .username(userEntity.getUsername())
                                                        .password(userEntity.getPassword())
                                                        .email(userEntity.getEmail())
                                                        .phone(userEntity.getPhone())
                                                        .build());
        }
}
