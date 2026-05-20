package co.basketlove.api.cart.service.impl;

import co.basketlove.api.cart.dto.AddToCartRequest;
import co.basketlove.api.cart.dto.CartItemResponse;
import co.basketlove.api.cart.dto.CartResponse;
import co.basketlove.api.cart.entity.CartItem;
import co.basketlove.api.cart.mapper.CartMapper;
import co.basketlove.api.cart.repository.CartItemRepository;
import co.basketlove.api.cart.service.CartService;
import co.basketlove.api.exception.ResourceNotFoundException;
import co.basketlove.api.products.entity.Product;
import co.basketlove.api.products.repository.ProductRepository;
import co.basketlove.api.users.entity.User;
import co.basketlove.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.basketlove.api.security.util.SecurityUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository repository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartMapper mapper;

    @Override
    @Transactional
    public void add(
            AddToCartRequest request
    ) {

        User user = getCurrentUser();

        Product product = productRepository
                .findById(request.productId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"
                        ));

        CartItem item = repository
                .findByUserIdAndProductId(
                        user.getId(),
                        request.productId()
                )
                .orElse(null);

        if (item == null) {

            item = new CartItem();

            item.setUser(user);
            item.setProduct(product);
            item.setQuantity(request.quantity());

        } else {

            item.setQuantity(
                    item.getQuantity() + request.quantity()
            );
        }

        repository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponse getCart() {

        User user = getCurrentUser();

        var items = repository.findByUserId(user.getId())
                .stream()
                .map(mapper::toResponse)
                .toList();

        var total = items.stream()
                .map(item -> item.subtotal())
                .reduce(
                        java.math.BigDecimal.ZERO,
                        java.math.BigDecimal::add
                );

        return new CartResponse(items, total);
    }

    @Override
    @Transactional
    public void removeItem(Long itemId) {

        User user = getCurrentUser();

        CartItem item = repository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart item not found"
                        ));

        if (!item.getUser().getId().equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Cart item not found"
            );
        }

        repository.delete(item);
    }

    @Override
    @Transactional
    public void updateQuantity(
            Long itemId,
            Integer quantity
    ) {

        User user = getCurrentUser();

        CartItem item = repository.findById(itemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart item not found"
                        ));

        if (!item.getUser().getId().equals(user.getId())) {

            throw new ResourceNotFoundException(
                    "Cart item not found"
            );
        }

        item.setQuantity(quantity);

        repository.save(item);
    }

    private User getCurrentUser() {

        String email =
                SecurityUtils.getCurrentUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));
    }
}