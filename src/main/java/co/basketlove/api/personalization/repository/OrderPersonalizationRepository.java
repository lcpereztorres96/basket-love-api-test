package co.basketlove.api.personalization.repository;

import co.basketlove.api.personalization.entity.OrderPersonalization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPersonalizationRepository
        extends JpaRepository<OrderPersonalization, Long> {
}