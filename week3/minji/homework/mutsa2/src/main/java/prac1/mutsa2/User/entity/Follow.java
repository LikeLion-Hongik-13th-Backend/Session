package prac1.mutsa2.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "userId")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "userId")
    private User following;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
