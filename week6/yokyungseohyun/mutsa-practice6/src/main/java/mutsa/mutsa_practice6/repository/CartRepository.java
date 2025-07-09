package mutsa.mutsa_practice6.repository;

import mutsa.mutsa_practice6.entity.Cart;
import mutsa.mutsa_practice6.entity.CartItem;
import mutsa.mutsa_practice6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser_UserId(Long userId);
}
