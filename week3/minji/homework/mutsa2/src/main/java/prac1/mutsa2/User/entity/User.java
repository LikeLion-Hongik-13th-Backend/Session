package prac1.mutsa2.User.entity;


import jakarta.persistence.*;
import lombok.*;
import prac1.mutsa2.Post.entity.PostComment;
import prac1.mutsa2.Post.entity.PostLike;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private Integer age;
    private String email;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<PostLike> postLikes;

    @Builder.Default
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followingList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PostComment> postComments;
}
