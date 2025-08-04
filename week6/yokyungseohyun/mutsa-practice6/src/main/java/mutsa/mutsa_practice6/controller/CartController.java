package mutsa.mutsa_practice6.controller;

import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.AddCartItemRequestDto;
import mutsa.mutsa_practice6.dto.response.AddCartItemResponseDto;
import mutsa.mutsa_practice6.dto.response.ApiResponse;
import mutsa.mutsa_practice6.dto.response.CartResponseDto;
import mutsa.mutsa_practice6.dto.response.DeleteCartItemResponseDto;
import mutsa.mutsa_practice6.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    //장바구니 조회
    @GetMapping
    public ResponseEntity<ApiResponse<CartResponseDto>> getCartItems(@RequestParam Long userId) {
        CartResponseDto response = cartService.getCartItems(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    //상품 추가
    @PostMapping
    public ResponseEntity<ApiResponse<AddCartItemResponseDto>> addCartItem(
            @RequestParam Long userId,
            @RequestBody AddCartItemRequestDto requestDto
    ) {
        AddCartItemResponseDto response = cartService.addCartItem(userId, requestDto);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    //상품 삭제
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<DeleteCartItemResponseDto>> deleteCartItem(
            @RequestParam Long userId,
            @PathVariable Long productId
    ) {
        DeleteCartItemResponseDto response = cartService.deleteCartItem(userId, productId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
