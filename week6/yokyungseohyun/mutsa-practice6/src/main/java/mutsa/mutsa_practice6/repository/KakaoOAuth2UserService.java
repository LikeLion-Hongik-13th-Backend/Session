package mutsa.mutsa_practice6.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mutsa.mutsa_practice6.dto.KakaoUserInfo;
import mutsa.mutsa_practice6.entity.CustomOAuth2User;
import mutsa.mutsa_practice6.entity.Role;
import mutsa.mutsa_practice6.entity.SocialType;
import mutsa.mutsa_practice6.entity.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("카카오 OAuth2 로그인 시작");

        // 부모 클래스에서 카카오 사용자 정보 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 카카오에서 받은 사용자 정보 로그 출력 (디버깅용)
        log.info("카카오 사용자 정보: {}", oAuth2User.getAttributes());

        // 카카오 사용자 정보 파싱
        String socialId = oAuth2User.getName();  // 카카오 사용자 ID
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("socialId: {}", socialId);

        // 카카오 사용자 정보 추출
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(attributes);

        // 기존 사용자인지 확인하고 회원가입/로그인 처리
        User user = saveOrUpdate(kakaoUserInfo);

        // Spring Security에서 사용할 사용자 정보 반환
        return new CustomOAuth2User(user, attributes);
    }

    /**
     * 카카오 사용자 정보를 바탕으로 회원가입 또는 정보 업데이트
     * @param kakaoUserInfo 카카오에서 받은 사용자 정보
     * @return 저장된 사용자 정보
     */
    private User saveOrUpdate(KakaoUserInfo kakaoUserInfo) {
        // 기존 사용자 찾기 (카카오 ID로 검색)
        Optional<User> existingUser = userRepository.findBySocialIdAndSocialType(
                kakaoUserInfo.getId(), SocialType.KAKAO);

        if (existingUser.isPresent()) {
            // 기존 사용자라면 정보 업데이트
            User user = existingUser.get();
            user.updateNickname(kakaoUserInfo.getNickname());
            return userRepository.save(user);
        } else {
            // 신규 사용자라면 회원가입
            User newUser = User.builder()
                    .nickname(kakaoUserInfo.getNickname())
                    .socialId(kakaoUserInfo.getId())
                    .socialType(SocialType.KAKAO)
                    .role(Role.USER)  // 기본 권한
                    .build();

            return userRepository.save(newUser);
        }
    }
}
