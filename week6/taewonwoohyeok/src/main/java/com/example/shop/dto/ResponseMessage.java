package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ResponseMessage {

    private boolean success;
    private String message;

    private ResponseData responseData;

}
