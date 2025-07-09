package mutsa.mutsa_practice6.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCartItemRequestDto {
    private Long productId;
    private int quantity;
}
