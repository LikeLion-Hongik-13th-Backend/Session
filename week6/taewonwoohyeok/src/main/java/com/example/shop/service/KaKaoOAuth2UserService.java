package com.example.shop.service;

import com.example.shop.dto.KakaoUserInfo;
import com.example.shop.entity.CustomOAuth2User;
import com.example.shop.entity.Role;
import com.example.shop.entity.SocialType;
import com.example.shop.entity.User;
import com.example.shop.repository.UserRepository;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KaKaoOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("카카오 OAuth2 로그인 시작");

        //부모 클래스에서 카카오 사용자 정보 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("카카오 사용자 정보: {}", oAuth2User.getAttributes());

        String socialId = oAuth2User.getName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        log.info("socialId: {}", socialId);

        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(attributes);

        User user = saveOrUpdate(kakaoUserInfo);

        return new CustomOAuth2User(user, attributes);
    }

    private User saveOrUpdate(KakaoUserInfo kakaoUserInfo) {
        //기존 사용자 찾기(카카오 ID로 검색)
        Optional<User> existingUser = userRepository.findBySocialIdAndSocialType(
                kakaoUserInfo.getId(), SocialType.KAKAO);

        if (existingUser.isPresent()) {
            //기존 사용자라면 정보 업데이트
            User user = existingUser.get();
            user.updateNickname(kakaoUserInfo.getNickname());
            return userRepository.save(user);
        } else{
            User newUser = User.builder()
                    .nickname(kakaoUserInfo.getNickname())
                    .socialId(kakaoUserInfo.getId())
                    .socialType(SocialType.KAKAO)
                    .role(Role.USER)
                    .build();
            return userRepository.save(newUser);
        }
    }
}
