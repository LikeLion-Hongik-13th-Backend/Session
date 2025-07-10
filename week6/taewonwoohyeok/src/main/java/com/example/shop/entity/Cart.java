package com.example.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private boolean ordered;

    public Cart(User user) {
        this.user = user;
        this.ordered = false;
    }
}
