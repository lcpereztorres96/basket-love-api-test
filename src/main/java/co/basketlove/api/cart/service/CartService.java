package co.basketlove.api.cart.service;

import co.basketlove.api.cart.dto.AddToCartRequest;
import co.basketlove.api.cart.dto.CartResponse;

public interface CartService {

    void add(AddToCartRequest request);

    CartResponse getCart();

    void removeItem(Long itemId);

    void updateQuantity(
            Long itemId,
            Integer quantity
    );
}