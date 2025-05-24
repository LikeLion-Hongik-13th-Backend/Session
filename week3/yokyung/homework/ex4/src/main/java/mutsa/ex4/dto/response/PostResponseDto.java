package mutsa.ex4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor //필드를 모두 받는 생성자 -> new PostResponseDto(...) 가능
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private int view;
    private Long userId;
}
