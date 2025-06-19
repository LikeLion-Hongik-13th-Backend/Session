package mutsa.ex4.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowRequestDto {
    private Long followerId;
    private Long toUserId;
}
