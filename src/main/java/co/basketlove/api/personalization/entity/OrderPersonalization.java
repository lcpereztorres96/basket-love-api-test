package co.basketlove.api.personalization.entity;

import co.basketlove.api.orders.entity.Order;
import co.basketlove.api.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_personalizations")
@Getter
@Setter
public class OrderPersonalization extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, unique = true)
    private Order order;

    @Column(length = 2000)
    private String message;

    private String recipientName;

    private String senderName;

    private boolean anonymousSender;

    private String cardStyle;
}