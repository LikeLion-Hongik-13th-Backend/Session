package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_item",joinColumns = @JoinColumn(name="category_id"),inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<Item> items = new ArrayList<>();

    //내 부모니까 manytoone
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    //자식이니까 onetomany ( 나 자신 매핑 )
    @OneToMany(mappedBy="parent")
    private List<Category> child= new ArrayList<>();


}
