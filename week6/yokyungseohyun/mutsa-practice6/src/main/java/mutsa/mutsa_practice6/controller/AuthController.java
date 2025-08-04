package mutsa.mutsa_practice6.controller;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutsa.mutsa_practice6.dto.oauth.KakaoLoginRequest;
import mutsa.mutsa_practice6.dto.response.ApiResponse;
import mutsa.mutsa_practice6.dto.response.TokenResponse;
import mutsa.mutsa_practice6.dto.response.TokenValidationResponseDto;
import mutsa.mutsa_practice6.dto.response.UserResponseDto;
import mutsa.mutsa_practice6.entity.User;
import mutsa.mutsa_practice6.service.OAuthService;
import mutsa.mutsa_practice6.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final OAuthService oAuthService;
    private final UserService userService;

    @PostMapping("/kakao")
    public ResponseEntity<ApiResponse<TokenResponse>> kakaoLogin(@RequestBody KakaoLoginRequest request) {
        log.info("카카오 로그인 요청 - 인가 코드: {}", 
                request.getCode().substring(0, Math.min(request.getCode().length(), 10)) + "...");
        
        try {
            TokenResponse tokenResponse = oAuthService.kakaoLogin(request.getCode());
            log.info("카카오 로그인 성공 - 사용자: {}", tokenResponse.getUser().getNickname());
            return ResponseEntity.ok(ApiResponse.success(tokenResponse));
        } catch (Exception e) {
            log.error("카카오 로그인 실패: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponseDto>> getCurrentUserInfo(Authentication authentication) {
        User user = userService.findBySocialId(authentication.getName()); // authentication.getName()은 JwtFilter에서 설정한 socialId
        return ResponseEntity.ok(ApiResponse.success(UserResponseDto.from(user)));
    }

    @GetMapping("/validate")
    public ResponseEntity<ApiResponse<TokenValidationResponseDto>> validateToken(Authentication authentication) {
        return ResponseEntity.ok(ApiResponse.success(TokenValidationResponseDto.builder()
                .valid(true)
                .userSocialId(authentication.getName())
                .authorities(authentication.getAuthorities())
                .authenticated(authentication.isAuthenticated())
                .build()));
    }
}