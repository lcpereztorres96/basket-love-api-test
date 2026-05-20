package co.basketlove.api.orders.controller;

import co.basketlove.api.orders.dto.OrderResponse;
import co.basketlove.api.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/checkout/{userId}")
    public OrderResponse checkout(
            @PathVariable Long userId
    ) {

        return service.checkout(userId);
    }

    @GetMapping("/{userId}")
    public List<OrderResponse> findMyOrders(
            @PathVariable Long userId
    ) {

        return service.findMyOrders(userId);
    }
}