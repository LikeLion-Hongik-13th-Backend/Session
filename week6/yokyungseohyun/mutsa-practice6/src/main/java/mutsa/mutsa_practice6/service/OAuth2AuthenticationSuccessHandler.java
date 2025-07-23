package mutsa.mutsa_practice6.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutsa.mutsa_practice6.entity.CustomOAuth2User;
import mutsa.mutsa_practice6.entity.User;
import mutsa.mutsa_practice6.global.util.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 로그인 성공 시 처리하는 핸들러
 *
 * 역할:
 * 1. 카카오 로그인 성공 후 JWT 토큰 생성
 * 2. 프론트엔드로 토큰과 함께 리다이렉트
 *
 * 흐름:
 * 사용자 카카오 로그인 → 카카오에서 우리 서버로 리다이렉트
 * → 이 핸들러에서 JWT 생성 → 프론트엔드로 토큰과 함께 리다이렉트
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * OAuth2 로그인 성공 시 실행되는 메서드
     *
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @param authentication 인증 정보 (로그인한 사용자 정보 포함)
     * @throws IOException 입출력 예외
     * @throws ServletException 서블릿 예외
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("OAuth2 로그인 성공 처리 시작");

        // 1. 인증된 사용자 정보 가져오기
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        User user = oAuth2User.getUser();

        log.info("로그인 성공한 사용자: {} (소셜ID: {})", user.getNickname(), user.getSocialId());

        // 2. JWT 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(user.getSocialId());
        String refreshToken = jwtUtil.generateRefreshToken(user.getSocialId());

        // 3. 응답 데이터 구성
        Map<String, Object> responseData = createSuccessResponse(user, accessToken, refreshToken);

        // 4. JSON 응답으로 토큰 전달
        sendJsonResponse(response, responseData);

        log.info("OAuth2 로그인 성공 - JSON 응답 전송 완료");
    }

    private Map<String, Object> createSuccessResponse(User user, String accessToken, String refreshToken) {
        Map<String, Object> response = new HashMap<>();

        // 성공 상태
        response.put("success", true);
        response.put("message", "로그인 성공");
        response.put("timestamp", System.currentTimeMillis());

        // 토큰 정보
        Map<String, Object> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        tokens.put("tokenType", "Bearer");
        tokens.put("expiresIn", jwtUtil.getAccessTokenExpiration() / 1000); // 초 단위
        response.put("tokens", tokens);

        // 사용자 정보
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getUserId());
        userInfo.put("socialId", user.getSocialId());
        userInfo.put("nickname", user.getNickname());
        userInfo.put("role", user.getRole().name());
        userInfo.put("socialType", user.getSocialType().name());
        response.put("user", userInfo);

        // 다음 단계 안내
        response.put("nextStep", "프론트엔드에서 토큰을 localStorage에 저장하고 API 요청 시 Authorization 헤더에 포함");

        return response;
    }

    private void sendJsonResponse(HttpServletResponse response, Map<String, Object> data) throws IOException {
        // 응답 헤더 설정
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // CORS 헤더 설정 (프론트엔드 연동을 위해)
        response.setHeader("Access-Control-Allow-Origin", "https://youngjunn2.shop"); //http://localhost:3000
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // JSON 응답 작성
        String jsonResponse = objectMapper.writeValueAsString(data);
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

        log.debug("JSON 응답 전송: {}", jsonResponse);
    }
}

