package mutsa.mutsa_practice6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 메인 페이지와 기본적인 테스트 API를 제공하는 컨트롤러
 */
@Controller
public class MainController {

    /**
     * 메인 페이지
     * 로그인 버튼이 있는 간단한 HTML 페이지 반환
     *
     * @return 메인 페이지 HTML
     */
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>카카오 소셜 로그인 테스트</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        max-width: 600px;
                        margin: 50px auto;
                        padding: 20px;
                        text-align: center;
                    }
                    .login-btn {
                        background-color: #FEE500;
                        color: #000;
                        border: none;
                        padding: 12px 24px;
                        font-size: 16px;
                        border-radius: 6px;
                        cursor: pointer;
                        text-decoration: none;
                        display: inline-block;
                        margin: 10px;
                    }
                    .login-btn:hover {
                        background-color: #FDD700;
                    }
                    .api-test {
                        margin-top: 30px;
                        padding: 20px;
                        border: 1px solid #ddd;
                        border-radius: 8px;
                        text-align: left;
                    }
                    .code {
                        background-color: #f5f5f5;
                        padding: 10px;
                        border-radius: 4px;
                        font-family: monospace;
                        margin: 10px 0;
                    }
                </style>
            </head>
            <body>
                <h1>🚀 Spring Security + 카카오 로그인 + JWT 실습</h1>
                <p>IT 동아리 백엔드 세션에 오신 것을 환영합니다!</p>
                
                <div>
                    <a href="/oauth2/authorization/kakao" class="login-btn">
                        🥥 카카오로 로그인하기
                    </a>
                </div>
                
                <div class="api-test">
                    <h3>📋 API 테스트 가이드</h3>
                    
                    <h4>1. 로그인 후 토큰 확인</h4>
                    <p>카카오 로그인 성공 시 프론트엔드로 토큰이 전달됩니다.</p>
                    
                    <h4>2. API 테스트 (Postman 사용)</h4>
                    
                    <p><strong>사용자 정보 조회:</strong></p>
                    <div class="code">
                        GET http://localhost:8080/api/auth/me<br>
                        Authorization: Bearer {your-jwt-token}
                    </div>
                    
                    <p><strong>프로필 조회:</strong></p>
                    <div class="code">
                        GET http://localhost:8080/api/user/profile<br>
                        Authorization: Bearer {your-jwt-token}
                    </div>
                    
                    <p><strong>닉네임 수정:</strong></p>
                    <div class="code">
                        PUT http://localhost:8080/api/user/profile<br>
                        Authorization: Bearer {your-jwt-token}<br>
                        Content-Type: application/json<br><br>
                        {<br>
                        &nbsp;&nbsp;"nickname": "새로운닉네임"<br>
                        }
                    </div>
                    
                    <p><strong>토큰 검증:</strong></p>
                    <div class="code">
                        POST http://localhost:8080/api/auth/validate<br>
                        Authorization: Bearer {your-jwt-token}
                    </div>
                </div>
                
                <div class="api-test">
                    <h3>🔍 개발자 도구</h3>
                    <p><a href="/h2-console" target="_blank">H2 데이터베이스 콘솔</a> (개발용)</p>
                    <p>JDBC URL: jdbc:h2:mem:testdb</p>
                    <p>사용자명: sa, 비밀번호: (빈칸)</p>
                </div>
                
                <div style="margin-top: 30px; font-size: 14px; color: #666;">
                    <p>💡 이 프로젝트는 학습용입니다. 실제 프로덕션에서는 보안을 더 강화해야 합니다.</p>
                </div>
            </body>
            </html>
            """;
    }

    /**
     * 간단한 로그인 페이지
     * OAuth2 로그인이 실패했을 때 보여줄 페이지
     *
     * @return 로그인 페이지 HTML
     */
    @GetMapping("/login")
    @ResponseBody
    public String loginPage() {
        return """
            <!DOCTYPE html>
            <html lang="ko">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>로그인</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        max-width: 400px;
                        margin: 100px auto;
                        padding: 20px;
                        text-align: center;
                    }
                    .login-btn {
                        background-color: #FEE500;
                        color: #000;
                        border: none;
                        padding: 15px 30px;
                        font-size: 16px;
                        border-radius: 8px;
                        cursor: pointer;
                        text-decoration: none;
                        display: inline-block;
                        margin: 20px 0;
                    }
                    .login-btn:hover {
                        background-color: #FDD700;
                    }
                </style>
            </head>
            <body>
                <h2>🔐 로그인이 필요합니다</h2>
                <p>카카오 계정으로 간편하게 로그인하세요.</p>
                
                <a href="/oauth2/authorization/kakao" class="login-btn">
                    🥥 카카오로 로그인
                </a>
                
                <p><a href="/">← 메인 페이지로 돌아가기</a></p>
            </body>
            </html>
            """;
    }

    /**
     * API 상태 확인
     * 서버가 정상 동작하는지 확인하는 헬스체크 API
     *
     * @return 서버 상태 정보
     */
    @GetMapping("/api/health")
    @ResponseBody
    public Map<String, Object> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("message", "서버가 정상적으로 동작 중입니다.");
        health.put("timestamp", System.currentTimeMillis());
        health.put("version", "1.0.0");

        return health;
    }

    /**
     * 공개 API 예시
     * 인증 없이 접근 가능한 API
     *
     * @return 공개 정보
     */
    @GetMapping("/api/public/info")
    @ResponseBody
    public Map<String, Object> publicInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("message", "이것은 인증이 필요없는 공개 API입니다.");
        info.put("features", new String[]{
                "카카오 소셜 로그인",
                "JWT 토큰 인증",
                "Spring Security 적용",
                "RESTful API"
        });
        info.put("documentation", "API 문서는 추후 Swagger로 제공 예정");

        return info;
    }
}

