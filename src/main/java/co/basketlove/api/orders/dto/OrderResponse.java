package co.basketlove.api.orders.dto;

import co.basketlove.api.orders.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(

        Long id,

        BigDecimal total,

        OrderStatus status,

        LocalDateTime createdAt,

        List<OrderItemResponse> items
) {
}