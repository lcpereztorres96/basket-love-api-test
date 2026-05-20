package co.basketlove.api.orders.repository;

import co.basketlove.api.orders.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {
            "items",
            "items.product"
    })
    List<Order> findByUserId(Long userId);
}