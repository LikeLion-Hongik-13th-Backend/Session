package com.example.shop.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartResponse implements ResponseData{

    private Long cartId;
    private Long productId;
    private LocalDateTime addedAt;
}
