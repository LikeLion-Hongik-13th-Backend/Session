package com.example.shop.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartItemListResponse {

    List<CartItemResponse> cartItems;
    int cartTotalQuantity;
    int cartTotalPrice;

    public static CartItemListResponse from(List<CartItemResponse> cartItemResponses) {
        int totalQuantity = cartItemResponses.stream()
                .mapToInt(CartItemResponse::getQuantity)
                .sum();

        int totalPrice = cartItemResponses.stream()
                .mapToInt(CartItemResponse::getTotalPrice)
                .sum();
        return new CartItemListResponse(cartItemResponses, totalQuantity, totalPrice);
    }
}
