package com.example.shop.dto;

import com.example.shop.entity.Address;
import com.example.shop.entity.User;
import jakarta.persistence.Embedded;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;

public class UserRequest {

    private String name;
    private String email;
    private String phone_number;
    //주소 추가
    private Address address;
    private String password;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .phone_number(phone_number)
                .address(this.address)
                .password(this.password)
                .build();
    }
}
