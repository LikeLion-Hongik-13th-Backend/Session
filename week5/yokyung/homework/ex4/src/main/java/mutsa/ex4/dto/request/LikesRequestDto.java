package mutsa.ex4.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikesRequestDto {
    private Long likeId;
    private Long postId;
    private Long userId;
}
