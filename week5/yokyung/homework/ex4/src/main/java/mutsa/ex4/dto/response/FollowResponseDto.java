package mutsa.ex4.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FollowResponseDto {
    private Long followId;
    private Long fromUserId;
    private Long toUserId;
}
