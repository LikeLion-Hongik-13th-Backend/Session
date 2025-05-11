package prac1.mutsa2.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private List<Long> categoryIds;
    private Long userId; //로그인 과정 없으므로..
}
