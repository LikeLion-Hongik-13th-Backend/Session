package com.example.shop.dto;

import com.example.shop.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private int price;
    private int stock;
    private String description;
    private String category;

    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .category(product.getCategory())
                .build();
    }
}
