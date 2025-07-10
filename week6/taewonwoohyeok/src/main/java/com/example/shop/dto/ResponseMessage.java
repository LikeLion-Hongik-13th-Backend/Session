package com.example.shop.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseMessage {

    private boolean success;
    private String message;

    private ResponseData responseData;

}
