package co.basketlove.api.orders.service;

import co.basketlove.api.orders.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse checkout(Long userId);

    List<OrderResponse> findMyOrders(Long userId);
}