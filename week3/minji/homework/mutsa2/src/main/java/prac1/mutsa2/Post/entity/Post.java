package prac1.mutsa2.Post.entity;


import jakarta.persistence.*;
import lombok.*;
import prac1.mutsa2.User.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name="view_count")
    private Integer viewCount;

    private String content;

    private String title;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<PostCategory> postCategoryList;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post")
    private List<PostComment> postComments;

}
