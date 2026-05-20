package co.basketlove.api.orders.service.impl;

import co.basketlove.api.cart.entity.CartItem;
import co.basketlove.api.cart.repository.CartItemRepository;
import co.basketlove.api.exception.BusinessException;
import co.basketlove.api.exception.ResourceNotFoundException;
import co.basketlove.api.orders.dto.OrderItemResponse;
import co.basketlove.api.orders.dto.OrderResponse;
import co.basketlove.api.orders.entity.Order;
import co.basketlove.api.orders.entity.OrderItem;
import co.basketlove.api.orders.repository.OrderRepository;
import co.basketlove.api.orders.service.OrderService;
import co.basketlove.api.users.entity.User;
import co.basketlove.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CartItemRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderResponse checkout(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        List<CartItem> cartItems =
                cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {

            throw new BusinessException("Cart is empty");
        }

        Order order = new Order();

        order.setUser(user);

        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());

            BigDecimal subtotal =
                    cartItem.getProduct()
                            .getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            cartItem.getQuantity()
                                    )
                            );

            item.setSubtotal(subtotal);

            total = total.add(subtotal);

            order.getItems().add(item);
        }

        order.setTotal(total);

        repository.save(order);

        cartRepository.deleteAll(cartItems);

        return mapToResponse(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> findMyOrders(Long userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private OrderResponse mapToResponse(Order order) {

        List<OrderItemResponse> items =
                order.getItems()
                        .stream()
                        .map(item -> new OrderItemResponse(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getPrice(),
                                item.getSubtotal()
                        ))
                        .toList();

        return new OrderResponse(
                order.getId(),
                order.getTotal(),
                order.getStatus(),
                order.getCreatedAt(),
                items
        );
    }
}