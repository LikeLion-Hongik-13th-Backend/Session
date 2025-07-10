package com.example.shop.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String street;
    private String detail;
}
