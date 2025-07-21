package com.example.shop.global.config;

import com.example.shop.global.JwtTokenFilter;
import com.example.shop.service.KaKaoOAuth2UserService;
import com.example.shop.service.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final KaKaoOAuth2UserService kaKaoOAuth2UserService;
    private final JwtTokenFilter jwtTokenFilter;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // REST API는 세션 기반 인증이 아니라 토큰 기반 인증이므로 SCRF 공격 위험이 적음으로
                // CSRF 공격 방어 꺼버림
                .csrf(csrf -> csrf.disable())

                //cors 설정 config 설정파일이 별도로 존재하므로 일단 추가 x
                // == 세션 관리 설정 ===
                // JWT 토큰을 사용하므로 세션을 생성하지 않음
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //요청 권한 설정
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/",                    // 메인 페이지
                                "/login/**",            // 로그인 관련
                                "/oauth2/**",          // OAuth2 관련
                                "/h2-console/**",      // H2 데이터베이스 콘솔
                                "/api/auth/**",   // 인증 관련 API
                                "/static/**",
                                "/users/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/login")
                        .userInfoEndpoint(userInfo ->
                                userInfo.userService(kaKaoOAuth2UserService))
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
