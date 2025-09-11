package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jpabook.jpashop.domain.Address;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id@GeneratedValue
    //pk여서 column을 줘야 함.
    //테이블명 + id로 사용 (일관성 있게 사용하기 !)
    @Column(name = "member_id")
    private Long id;

    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy="member")
    //한명의 회원이 여러개의 상품을 주문
    private List<Order> orders=new ArrayList<>();
}
