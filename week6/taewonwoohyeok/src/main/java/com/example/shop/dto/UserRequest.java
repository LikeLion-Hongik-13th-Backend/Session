package com.example.shop.dto;

import com.example.shop.entity.Address;
import com.example.shop.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String nickname;

    private String email;

    private String phoneNumber;

    private String password;

    private Address address;

    public User toEntity() {
        return User.builder()
                .nickname(this.nickname)
                .email(this.email)
                .phone_number(this.phoneNumber)
                .address(this.address)
                .password(this.password)
                .build();
    }
}
