package mutsa.mutsa_practice6.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCartItemResponseDto {
    private Data data;
    private boolean success;

    @Getter
    @Builder
    public static class Data {
        private Long productId;
        private String addedAt;
    }
}
