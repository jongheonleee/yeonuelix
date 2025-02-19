package yeo.nuel.lix.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import yeo.nuel.lix.user.LogUserAuditHistoryUseCase;
import yeo.nuel.lix.user.UserHistoryService;

@Component
@RequiredArgsConstructor
public class UserHistoryLoggingFilter extends OncePerRequestFilter {

    private final LogUserAuditHistoryUseCase useCase;
    private final UserHistoryService userHistoryService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CompletableFuture.runAsync(() -> log(authentication, request));

        filterChain.doFilter(request, response);
    }

    public void log(Authentication authentication, HttpServletRequest request) {
        useCase.log(
                authentication.getName(),
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")),
                request.getRemoteAddr(),
                request.getMethod(),
                request.getRequestURI(),
                getHeader(request),
                "payload"
        );
    }

    private String getHeader(HttpServletRequest request) {
        // 헤더를 저장할 Map 생성
        Map<String, String> headersMap = new HashMap<>();

        // 모든 헤더 추출
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headersMap.put(headerName, headerValue);
            }
        }

        // Map을 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(headersMap);
        } catch (JsonProcessingException e) {
            return "{}"; // 변환 실패시 빈 JSON 반환
        }


    }
}
