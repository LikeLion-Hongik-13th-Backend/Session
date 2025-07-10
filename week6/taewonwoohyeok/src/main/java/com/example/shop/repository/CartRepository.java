package com.example.shop.repository;


import com.example.shop.entity.Cart;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUserUserId(Long userId);
}
