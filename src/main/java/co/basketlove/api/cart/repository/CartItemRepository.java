package co.basketlove.api.cart.repository;

import co.basketlove.api.cart.entity.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {

    @EntityGraph(attributePaths = {
            "product",
            "product.category"
    })
    List<CartItem> findByUserId(Long userId);

    Optional<CartItem> findByUserIdAndProductId(
            Long userId,
            Long productId
    );
}