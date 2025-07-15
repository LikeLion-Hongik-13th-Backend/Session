package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartItemQuantityRequest {
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
