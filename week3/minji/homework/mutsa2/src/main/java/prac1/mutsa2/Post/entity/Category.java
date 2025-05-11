package prac1.mutsa2.Post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String category;
    @OneToMany(mappedBy = "category")

    private List<PostCategory> postCategoryList;
}
