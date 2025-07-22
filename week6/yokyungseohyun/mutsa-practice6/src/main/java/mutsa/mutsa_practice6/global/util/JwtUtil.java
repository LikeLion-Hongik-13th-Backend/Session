package mutsa.mutsa_practice6.global.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT 토큰 생성 및 검증을 담당하는 유틸리티 클래스
 *
 * JWT란?
 * - JSON Web Token의 줄임말
 * - Header.Payload.Signature 형태로 구성
 * - 서버에서 상태를 저장하지 않고도 사용자 인증 가능
 */
@Component
@Slf4j  // 로그 출력을 위한 어노테이션
public class JwtUtil {

    // application.yml에서 설정한 JWT 비밀키와 만료시간
    @Value("${spring.jwt.secret}")
    private String secretKey;

    @Value("${spring.jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${spring.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    public String generateToken(String socialId, long expiration, TokenType tokenType) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(socialId) // 토큰의 주체 (사용자 식별자로 socialId 사용)
                .claim("type", tokenType)
                .setIssuedAt(now)       // 토큰 발급 시간
                .setExpiration(expiryDate)  // 토큰 만료 시간
                .signWith(getSigningKey())  // 서명 (토큰 위변조 방지)
                .compact();  // 최종 토큰 문자열 생성
    }

    public String generateRefreshToken(String socialId) {
        return generateToken(socialId, refreshTokenExpiration, TokenType.REFRESH);
    }

    public String generateAccessToken(String socialId) {
        return generateToken(socialId, accessTokenExpiration, TokenType.ACCESS);
    }

    public String getSocialIdFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();  // subject에 저장된 소셜 ID 반환
        } catch (JwtException e) {
            log.error("토큰에서 사용자 소셜 ID 추출 실패: {}", e.getMessage());
            return null;
        }
    }

    /**
     * JWT 토큰 유효성 검증
     * @param token 검증할 토큰
     * @return 유효하면 true, 무효하면 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("잘못된 형식의 JWT 토큰: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("JWT 서명 검증 실패: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 비어있음: {}", e.getMessage());
        }
        return false;
    }

    /**
     * JWT 토큰의 남은 만료 시간 확인
     * @param token JWT 토큰
     * @return 남은 시간 (밀리초), 만료되었으면 음수
     */
    public long getExpirationTime(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            return expiration.getTime() - System.currentTimeMillis();
        } catch (JwtException e) {
            log.error("토큰 만료 시간 확인 실패: {}", e.getMessage());
            return -1;
        }
    }

    /**
     * JWT 서명에 사용할 비밀키 생성
     * HMAC-SHA 알고리즘을 사용하여 안전한 키 생성
     * @return 서명용 비밀키
     */
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

}