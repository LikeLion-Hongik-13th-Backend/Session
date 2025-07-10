package com.example.shop.repository;

import com.example.shop.entity.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart_CartId(Long cartId);
}
