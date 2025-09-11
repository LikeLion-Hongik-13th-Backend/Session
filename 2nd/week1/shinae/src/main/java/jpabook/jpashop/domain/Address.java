package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private  String city;
    private  String street;
    private String zipcode;

    protected Address() {}
    /*
    값 타임은 변경 불가능하게 설계해야 한다
    엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 설정
     */

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
