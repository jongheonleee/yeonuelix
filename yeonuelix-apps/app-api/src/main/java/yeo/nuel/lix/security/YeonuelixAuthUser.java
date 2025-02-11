package yeo.nuel.lix.security;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class YeonuelixAuthUser extends User {
    private final String userId;
    private final String username;
    private final String password;
    private final String email;
    private final String phone;

    public YeonuelixAuthUser(String userId, String username, String password, String email, String phone, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
