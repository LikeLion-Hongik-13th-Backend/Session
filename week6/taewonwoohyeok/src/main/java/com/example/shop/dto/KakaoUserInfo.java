package com.example.shop.dto;

import java.util.Map;
import lombok.Getter;

@Getter
public class KakaoUserInfo {

    private String id;
    private String nickname;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.id = String.valueOf(attributes.get("id"));
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        this.nickname = (String) properties.get("nickname");
    }
}
