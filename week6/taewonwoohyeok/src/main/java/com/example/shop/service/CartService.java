package com.example.shop.service;

import com.example.shop.dto.CartItemListResponse;
import com.example.shop.dto.CartItemResponse;
import com.example.shop.dto.CartRequest;
import com.example.shop.dto.CartResponse;
import com.example.shop.entity.Cart;
import com.example.shop.entity.CartItem;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.repository.CartItemRepository;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public CartResponse addToCart(Long userId, CartRequest cartRequest) {
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new IllegalStateException("해당 Id의 상품이 존재하지 않습니다."));
        List<Cart> allByUserUserId = cartRepository.findAllByUserUserId(userId);
        Cart cartEntity = allByUserUserId.stream()
                .filter(cart -> !cart.isOrdered())
                .findFirst()
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new IllegalStateException("해당 id를 가진 user가 존재하지 않습니다."));
                    return cartRepository.save(new Cart(user));
                });
        CartItem cartItem = CartItem.builder()
                .product(product)
                .cart(cartEntity)
                .quantity(cartRequest.getQuantity())
                .build();
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return new CartResponse(savedCartItem.getProduct().getProductId(),
                savedCartItem.getAddedAt());
    }

    public CartItemListResponse getCartItems(Long cartId) {
        if(!cartRepository.existsById(cartId)){
            throw new IllegalStateException("해당 Id를 가진 카트가 존재하지 않습니다.");
        }
        List<CartItemResponse> responseList = cartItemRepository.findByCart_CartId(cartId).stream()
                .map(CartItemResponse::from).toList();
        return CartItemListResponse.from(responseList);
    }

    @Transactional
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalStateException("해당 Id를 가진 CartItem이 존재하지 않습니다."));
        cartItemRepository.delete(cartItem);
    }

    @Transactional
    public void updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalStateException("해당 id를 가진 cartItem을 찾을 수 없습니다."));
        cartItem.setQuantity(quantity);
    }


}
