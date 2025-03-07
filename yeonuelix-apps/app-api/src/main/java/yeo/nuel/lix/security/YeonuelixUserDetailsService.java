package yeo.nuel.lix.security;

import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yeo.nuel.lix.user.FetchUserUseCase;
import yeo.nuel.lix.user.response.UserResponse;

@Service
@RequiredArgsConstructor
public class YeonuelixUserDetailsService implements UserDetailsService {

    private final FetchUserUseCase fetchUserUseCase;

    // 사용자 정보를 이메일 기반으로 조회
    @Override
    public YeonuelixAuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
        UserResponse userByEmail = fetchUserUseCase.findUserByEmail(email);
        return new YeonuelixAuthUser(
                userByEmail.getUserId(),
                userByEmail.getUsername(),
                userByEmail.getPassword(),
                userByEmail.getEmail(),
                userByEmail.getPhone(),
                List.of(new SimpleGrantedAuthority(StringUtils.isBlank(userByEmail.getRole()) ? "-" : userByEmail.getRole()))
        );
    }
}
