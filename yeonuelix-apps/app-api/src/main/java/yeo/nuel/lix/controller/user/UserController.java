package yeo.nuel.lix.controller.user;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yeo.nuel.lix.controller.YeonuelixApiResponse;
import yeo.nuel.lix.controller.user.request.UserLoginRequest;
import yeo.nuel.lix.controller.user.request.UserRegisterRequest;
import yeo.nuel.lix.security.YeonuelixAuthUser;
import yeo.nuel.lix.user.RegisterUserUseCase;
import yeo.nuel.lix.user.command.UserRegistrationCommand;
import yeo.nuel.lix.user.response.UserRegistrationResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // 회원가입 처리
    @PostMapping("/api/v1/user/register")
    public YeonuelixApiResponse<UserRegistrationResponse> register(@RequestBody UserRegisterRequest request) {
        UserRegistrationResponse register = registerUserUseCase.register(
                UserRegistrationCommand.builder()
                                        .username(request.getUsername())
                                        .encryptedPassword(request.getPassword())
                                        .email(request.getEmail())
                                        .phone(request.getPhone())
                                        .build()
        );

        return YeonuelixApiResponse.ok(register);
    }

    // 로그인 처리
    @PostMapping("/api/v1/user/login")
    public YeonuelixApiResponse<String> login(@RequestBody UserLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        // Authentication 토큰 생성
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(token);

        // SecurityContext에서 인증 정보를 조회
        YeonuelixAuthUser principal = (YeonuelixAuthUser) authenticate.getPrincipal();

        // 토큰값 조회 및 반환 -> 프론트엔드에선 토큰값 활용
        return YeonuelixApiResponse.ok("access-token");
    }

    // 소셜 로그인 처리
    @PostMapping("/api/v1/user/callback")
    public YeonuelixApiResponse<String> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        return YeonuelixApiResponse.ok(null);
    }
}
