package mutsa.mutsa_practice6.repository;

import mutsa.mutsa_practice6.entity.Cart;
import mutsa.mutsa_practice6.entity.CartItem;
import mutsa.mutsa_practice6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

    List<CartItem> findAllByCart(Cart cart);
}
