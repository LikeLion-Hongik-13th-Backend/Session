package prac1.mutsa2.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequestDto {

    private Long userId;
    private Long postId;
    private String comment;

}
