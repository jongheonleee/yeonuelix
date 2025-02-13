package yeo.nuel.lix.config;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import yeo.nuel.lix.security.YeonuelixUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // 유저 정보 조회
    private final YeonuelixUserDetailsService yeonuelixUserDetailsService;

    // 시큐리티 필터 체인 등록
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 프론트엔드 리액트로 개발 -> form 로그인, http basic 방식 비활성화 처리
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);
        httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // 요청에 대한 인증 처리 -> 모든 요청에 대해 인증 요구
        httpSecurity.authorizeHttpRequests(auth ->
                    auth.requestMatchers(
                                    "/api/v1/user/register",
                                    "/api/v1/user/login"
                            ).permitAll() // 회원가입,로그인 요청은 인증 없이 허용
                        .anyRequest().authenticated()
        );

        // oauth2 관련 설정
//        httpSecurity.oauth2Login(oauth2 -> oauth2.failureUrl("/login?error=true"));


        // UserDetailsService 설정
        httpSecurity.userDetailsService(yeonuelixUserDetailsService);


        return httpSecurity.build();
    }

    // 비밀번호 암호화 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            return configuration;
        };
    }
}
