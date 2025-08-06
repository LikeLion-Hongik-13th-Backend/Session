package mutsa.mutsa_practice6.global;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.entity.User;
import mutsa.mutsa_practice6.global.util.JwtUtil;
import mutsa.mutsa_practice6.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    
    @PostConstruct
    public void init() {
        System.out.println("========================================");
        System.out.println("JwtTokenFilter Bean이 생성되었습니다!");
        System.out.println("JwtUtil 주입 확인: " + (jwtUtil != null ? "성공" : "실패"));
        System.out.println("UserRepository 주입 확인: " + (userRepository != null ? "성공" : "실패"));
        System.out.println("========================================");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //에러 때문에 추가한 코드
        String requestURI = request.getRequestURI();
        System.out.println("===== JwtTokenFilter 실행됨 =====");
        System.out.println("Request URI: " + requestURI);
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));
        log.info("필터 통과 요청 URI: {}", requestURI);  // ★ 이거 넣으세요

        // OAuth2 콜백 경로는 JWT 인증 필터에서 예외 처리
        if (requestURI.startsWith("/auth/kakao") ||
                requestURI.startsWith("/auth/") ||
                requestURI.startsWith("/api/oauth/")) {
            log.info("예외 처리됨 (필터 통과): {}", requestURI); // ★ 이거도 넣기
            filterChain.doFilter(request, response);
            return;
        }

        //여기까지

        String token = getTokenFromRequest(request);

        if(token != null && jwtUtil.validateToken(token)) {
            String socialId = jwtUtil.getSocialIdFromToken(token);

            if(socialId != null) {
                Optional<User> user = userRepository.findBySocialId(socialId);
                if(user.isPresent()) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user.get(),
                                    null,
                                    user.get().getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // Authorization 헤더가 존재하고 "Bearer "로 시작하는지 확인
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // "Bearer " 부분을 제거하고 토큰만 반환
            return bearerToken.substring(7);
        }

        return null;
    }
}


