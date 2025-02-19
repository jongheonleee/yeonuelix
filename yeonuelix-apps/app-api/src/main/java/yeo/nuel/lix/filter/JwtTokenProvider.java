package yeo.nuel.lix.filter;

import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import yeo.nuel.lix.token.FetchTokenUseCase;
import yeo.nuel.lix.user.response.UserResponse;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final FetchTokenUseCase fetchTokenUseCase;

    public Authentication getAuthentication(String accessToken) {
        UserResponse user = fetchTokenUseCase.findUserByAccessToken(accessToken);
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        UserDetails principal = new User(
                user.getUsername(),
                StringUtils.isBlank(user.getPassword()) ? "password" : user.getPassword(),
                authorities
        );
        return new UsernamePasswordAuthenticationToken(principal, user.getUserId(), authorities);
    }
}
