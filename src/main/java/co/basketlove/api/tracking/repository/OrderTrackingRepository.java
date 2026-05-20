package co.basketlove.api.tracking.repository;

import co.basketlove.api.tracking.entity.OrderTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderTrackingRepository
        extends JpaRepository<OrderTracking, Long> {

    List<OrderTracking> findByOrderIdOrderByCreatedAtAsc(
            Long orderId
    );
}