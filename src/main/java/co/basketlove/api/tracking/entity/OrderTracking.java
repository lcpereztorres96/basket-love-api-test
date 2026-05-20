package co.basketlove.api.tracking.entity;

import co.basketlove.api.orders.entity.Order;
import co.basketlove.api.shared.entity.BaseEntity;
import co.basketlove.api.tracking.enums.TrackingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_tracking")
@Getter
@Setter
public class OrderTracking extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackingStatus status;

    private String description;
}