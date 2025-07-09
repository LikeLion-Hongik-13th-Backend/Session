package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private int price;
    private int stock;
    private String description;
    private String category;

    @CreatedDate
    private LocalDateTime registeredAt;
}