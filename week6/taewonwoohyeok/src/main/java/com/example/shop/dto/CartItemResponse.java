package com.example.shop.dto;

import com.example.shop.entity.CartItem;
import com.example.shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class CartItemResponse {

    Long cartItemId;
    Long productId;
    String name;
    String category;
    int price;
    int quantity;
    int totalPrice;

    public static CartItemResponse from(CartItem cartItem) {
        Product product =  cartItem.getProduct();
        int price = product.getPrice();
        int quantity = cartItem.getQuantity();
        return new CartItemResponse(cartItem.getCartItemId(), product.getProductId(),product.getName(),
                product.getCategory(), price, quantity, price * quantity);
    }
}
