package yeo.nuel.lix.controller.user;

import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yeo.nuel.lix.controller.YeonuelixApiResponse;
import yeo.nuel.lix.controller.user.request.UserLoginRequest;
import yeo.nuel.lix.controller.user.request.UserRegisterRequest;
import yeo.nuel.lix.security.YeonuelixAuthUser;
import yeo.nuel.lix.token.FetchTokenUseCase;
import yeo.nuel.lix.token.UpdateTokenUseCase;
import yeo.nuel.lix.user.FetchUserUseCase;
import yeo.nuel.lix.user.RegisterUserUseCase;
import yeo.nuel.lix.user.command.UserRegistrationCommand;
import yeo.nuel.lix.user.response.UserRegistrationResponse;
import yeo.nuel.lix.user.response.UserResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final FetchTokenUseCase fetchTokenUseCase;
    private final FetchUserUseCase fetchUserUseCase;
    private final UpdateTokenUseCase updateTokenUseCase;

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

    // 소셜 로그인 처리 -> 현재 카카오 로그인 처리하고 나서 카카오 서버랑 해당 부분이랑 매핑이 안되어 있음
    @PostMapping("/api/v1/user/callback")
    public YeonuelixApiResponse<String> kakaoCallback(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        String accessTokenFromKakao = fetchTokenUseCase.getTokenFromKakao(code);
        UserResponse kakaoUser = fetchUserUseCase.findKakaoUser(accessTokenFromKakao);

        // 소셜 사용자가 이미 존재하는지 확인
        UserResponse byProviderId = fetchUserUseCase.findByProviderId(kakaoUser.getProviderId());

        // 만약 존재하지 않으면, 회원가입을 진행
        if (ObjectUtils.isEmpty(byProviderId)) {
            registerUserUseCase.registerSocialUser(kakaoUser.getUsername(),
                                                   kakaoUser.getProvider(),
                                                   kakaoUser.getProviderId());
        }


        // 토큰을 발급해서 반환
        return YeonuelixApiResponse.ok(updateTokenUseCase.upsertToken(kakaoUser.getProviderId()));
    }
}
