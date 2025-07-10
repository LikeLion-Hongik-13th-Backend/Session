package com.example.shop.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String name;
    private String email;
    private String phone_number;
    @CreatedDate
    private LocalDateTime created_at;

    //주소 추가
    @Embedded
    private Address address;
    private String password;

}
