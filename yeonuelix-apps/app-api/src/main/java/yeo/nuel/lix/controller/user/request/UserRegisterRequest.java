package yeo.nuel.lix.controller.user.request;

import lombok.Getter;
import yeo.nuel.lix.annotation.PasswordEncryption;

@Getter
public class UserRegisterRequest {

    private final String username;

    @PasswordEncryption
    private String password;
    private final String email;
    private final String phone;

    public UserRegisterRequest(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
