package com.example.shop.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartResponse implements ResponseData{

    private Long productId;
    private LocalDateTime addedAt;
}
