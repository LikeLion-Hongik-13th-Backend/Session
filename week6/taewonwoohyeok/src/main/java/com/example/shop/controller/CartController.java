package com.example.shop.controller;



import com.example.shop.dto.CartItemListResponse;
import com.example.shop.dto.CartRequest;
import com.example.shop.dto.CartResponse;
import com.example.shop.dto.ResponseMessage;
import com.example.shop.dto.UpdateCartItemQuantityRequest;
import com.example.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;


    @PostMapping("/{userId}")
    public ResponseEntity<ResponseMessage> createCartItem(@PathVariable Long userId, @RequestBody
    CartRequest cartRequest) {
        CartResponse cartResponse = cartService.addToCart(userId, cartRequest);
        return ResponseEntity.ok(new ResponseMessage(true, "장바구니 추가 성공", cartResponse));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartItemListResponse> getCartItems(@PathVariable Long cartId) {
        CartItemListResponse cartItemListResponse = cartService.getCartItems(cartId);
        return ResponseEntity.ok(cartItemListResponse);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ResponseMessage> deleteCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok(new ResponseMessage(true, "삭제 성공", null));
    }

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<ResponseMessage> updateCartItem(@PathVariable Long cartItemId,
            @RequestBody UpdateCartItemQuantityRequest request) {
        cartService.updateCartItem(cartItemId, request);
        return ResponseEntity.ok(new ResponseMessage(true, "수정 성공", null));
    }
}
