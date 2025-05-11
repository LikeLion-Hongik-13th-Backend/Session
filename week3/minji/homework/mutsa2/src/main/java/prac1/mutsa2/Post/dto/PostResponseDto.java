package prac1.mutsa2.Post.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import prac1.mutsa2.Post.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private Integer viewCount;
    private String content;
    private String title;
    private LocalDateTime createdAt;
    private String writer;

    public static PostResponseDto from(Post post) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getName())
                .viewCount(post.getViewCount())
                .createdAt(post.getCreatedAt())
                .build();
    }

}
