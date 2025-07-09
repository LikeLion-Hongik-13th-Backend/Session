package mutsa.mutsa_practice6.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {
    private List<CartItemDto> cartItems;
    private int cartTotalItems;
    private int cartTotalPrice;

    @Getter
    @Builder
    public static class CartItemDto {
        private Long productId;
        private String productName;
        private String category;
        private int price;
        private int quantity;
        private int totalPrice;
    }
}
