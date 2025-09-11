package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.*;
@Embeddable
@Getter@Setter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //기본 생성자 호출
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city=city;
        this.street=street;
        this.zipcode=zipcode;
    }
}
