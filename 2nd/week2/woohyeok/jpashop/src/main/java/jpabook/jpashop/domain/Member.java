package jpabook.jpashop.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private List<Order> orders = new ArrayList<>();
}
