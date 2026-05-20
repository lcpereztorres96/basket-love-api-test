package co.basketlove.api.cart.controller;

import co.basketlove.api.cart.dto.AddToCartRequest;
import co.basketlove.api.cart.dto.CartResponse;
import co.basketlove.api.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public void add(

            @Valid
            @RequestBody
            AddToCartRequest request
    ) {

        service.add(request);
    }

    @GetMapping
    public CartResponse getCart() {

        return service.getCart();
    }

    @DeleteMapping("/items/{itemId}")
    public void removeItem(
            @PathVariable Long itemId
    ) {

        service.removeItem(itemId);
    }

    @PatchMapping("/items/{itemId}")
    public void updateQuantity(

            @PathVariable Long itemId,

            @RequestParam Integer quantity
    ) {

        service.updateQuantity(
                itemId,
                quantity
        );
    }
}