package mutsa.mutsa_practice6.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") //모든 경로에 대해
                        .allowedOrigins("http://localhost:3000", "http://localhost:5173", "https://youngjunn2.shop") //허용할 프론트 도메인
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //허용할 HTTP 메서드
                        .allowedHeaders("*")
                        .allowCredentials(true); //쿠키 포함 허용시
            }
        };
    }
}