package mutsa.mutsa_practice6.dto.response;

import lombok.Builder;
import lombok.Getter;
import mutsa.mutsa_practice6.entity.User;

@Getter
@Builder
public class UserResponseDto  {
    private String socialId;
    private String nickname;
    private String role;
    private String socialType;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .socialId(user.getSocialId())
                .nickname(user.getNickname())
                .role(user.getRole().name())
                .socialType(user.getSocialType().name())
                .build();
    }
}
