package mutsa.mutsa_practice6.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mutsa.mutsa_practice6.dto.request.AddCartItemRequestDto;
import mutsa.mutsa_practice6.dto.response.AddCartItemResponseDto;
import mutsa.mutsa_practice6.dto.response.CartResponseDto;
import mutsa.mutsa_practice6.dto.response.DeleteCartItemResponseDto;
import mutsa.mutsa_practice6.dto.response.ProductResponseDto;
import mutsa.mutsa_practice6.entity.Cart;
import mutsa.mutsa_practice6.entity.CartItem;
import mutsa.mutsa_practice6.entity.Product;
import mutsa.mutsa_practice6.entity.User;
import mutsa.mutsa_practice6.exception.CustomException;
import mutsa.mutsa_practice6.exception.ErrorCode;
import mutsa.mutsa_practice6.repository.CartItemRepository;
import mutsa.mutsa_practice6.repository.CartRepository;
import mutsa.mutsa_practice6.repository.ProductRepository;
import mutsa.mutsa_practice6.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    
    // 장바구니 조회
    public CartResponseDto getCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder().user(user).build()
                ));

        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);

        List<CartResponseDto.CartItemDto> cartItemDtos = cartItems.stream()
                .map(cartItem -> {
                    Product product = cartItem.getProduct();
                    return CartResponseDto.CartItemDto.builder()
                            .productId(product.getProductId())
                            .productName(product.getProductName())
                            .category(product.getCategory())
                            .price(product.getPrice())
                            .quantity(cartItem.getQuantity())
                            .totalPrice(product.getPrice() * cartItem.getQuantity())
                            .build();
                })
                .collect(Collectors.toList());

        int totalItems = cartItemDtos.size();
        int totalPrice = cartItemDtos.stream()
                .mapToInt(CartResponseDto.CartItemDto::getTotalPrice)
                .sum();

        return CartResponseDto.builder()
                .cartItems(cartItemDtos)
                .cartTotalItems(totalItems)
                .cartTotalPrice(totalPrice)
                .build();
    }


    //상품 추가
    public AddCartItemResponseDto addCartItem(Long userId, AddCartItemRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder().user(user).build()
                ));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (cartItem.isPresent()) {
            CartItem existingItem = cartItem.get();
            existingItem.increaseQuantity(dto.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(dto.getQuantity())
                    .build();
            cart.getCartItems().add(newItem);
        }

        AddCartItemResponseDto.Data responseData = AddCartItemResponseDto.Data.builder()
                .productId(product.getProductId())
                .addedAt(LocalDateTime.now().toString())
                .build();

        return AddCartItemResponseDto.builder()
                .success(true)
                .data(responseData)
                .build();
    }

    //상품 삭제
    public DeleteCartItemResponseDto deleteCartItem(Long userId, Long productId) {

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.CART_NOT_FOUND));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        Optional<CartItem> cartItem = cartItemRepository.findByCartAndProduct(cart, product);

        if (cartItem.isPresent()) {
            cartItemRepository.delete(cartItem.get());
            return DeleteCartItemResponseDto.builder()
                    .success(true)
                    .message("장바구니에서 상품이 삭제되었습니다.")
                    .build();
        } else {
            return DeleteCartItemResponseDto.builder()
                    .success(false)
                    .message("해당 상품은 장바구니에 없습니다.")
                    .build();
        }

    }
}
