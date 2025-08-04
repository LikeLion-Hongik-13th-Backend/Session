package mutsa.mutsa_practice6.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutsa.mutsa_practice6.dto.oauth.KakaoTokenResponse;
import mutsa.mutsa_practice6.dto.oauth.KakaoUserInfo;
import mutsa.mutsa_practice6.dto.response.TokenResponse;
import mutsa.mutsa_practice6.entity.Role;
import mutsa.mutsa_practice6.entity.SocialType;
import mutsa.mutsa_practice6.entity.User;
import mutsa.mutsa_practice6.global.util.JwtUtil;
import mutsa.mutsa_practice6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OAuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public TokenResponse kakaoLogin(String code) {
        log.info("카카오 로그인 시작");

        // 1. 인가 코드로 액세스 토큰 받기
        KakaoTokenResponse kakaoToken = getKakaoToken(code);
        log.info("카카오 액세스 토큰 획득 성공");

        // 2. 액세스 토큰으로 사용자 정보 가져오기
        Map<String, Object> userInfo = getKakaoUserInfo(kakaoToken.getAccessToken());
        log.info("카카오 사용자 정보 조회 성공");

        // 3. 사용자 정보 파싱
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(userInfo);

        // 4. 사용자 저장 또는 업데이트
        User user = saveOrUpdate(kakaoUserInfo);
        log.info("사용자 정보 저장/업데이트 완료: {}", user.getNickname());

        // 5. JWT 토큰 생성
        String accessToken = jwtUtil.generateAccessToken(user.getSocialId());
        String refreshToken = jwtUtil.generateRefreshToken(user.getSocialId());
        log.info("JWT 토큰 생성 완료");

        // 6. 응답 생성
        return TokenResponse.builder()
                .success(true)
                .message("로그인 성공")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtUtil.getAccessTokenExpiration() / 1000)
                .user(TokenResponse.UserInfo.builder()
                        .id(user.getUserId())
                        .socialId(user.getSocialId())
                        .nickname(user.getNickname())
                        .role(user.getRole().name())
                        .socialType(user.getSocialType().name())
                        .build())
                .build();
    }

    private KakaoTokenResponse getKakaoToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("client_secret", kakaoClientSecret);
        params.add("redirect_uri", kakaoRedirectUri);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    KAKAO_TOKEN_URL,
                    request,
                    String.class
            );

            return objectMapper.readValue(response.getBody(), KakaoTokenResponse.class);
        } catch (Exception e) {
            log.error("카카오 토큰 요청 실패: {}", e.getMessage());
            throw new RuntimeException("카카오 토큰 획득 실패", e);
        }
    }

    private Map<String, Object> getKakaoUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    KAKAO_USER_INFO_URL,
                    HttpMethod.GET,
                    request,
                    Map.class
            );

            return response.getBody();
        } catch (Exception e) {
            log.error("카카오 사용자 정보 요청 실패: {}", e.getMessage());
            throw new RuntimeException("카카오 사용자 정보 조회 실패", e);
        }
    }

    private User saveOrUpdate(KakaoUserInfo kakaoUserInfo) {
        String socialId = kakaoUserInfo.getId();
        String nickname = kakaoUserInfo.getNickname();

        Optional<User> existingUser = userRepository.findBySocialId(socialId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.updateNickname(nickname);
            log.info("기존 사용자 정보 업데이트: {}", nickname);
            return userRepository.save(user);
        } else {
            User newUser = User.builder()
                    .socialId(socialId)
                    .nickname(nickname)
                    .socialType(SocialType.KAKAO)
                    .role(Role.USER)
                    .build();
            log.info("새 사용자 등록: {}", nickname);
            return userRepository.save(newUser);
        }
    }
}