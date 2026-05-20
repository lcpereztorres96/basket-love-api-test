package co.basketlove.api.cart.mapper;

import co.basketlove.api.cart.dto.CartItemResponse;
import co.basketlove.api.cart.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    @Mapping(target = "price", source = "product.price")
    @Mapping(
            target = "subtotal",
            expression = """
            java(
                item.getProduct()
                    .getPrice()
                    .multiply(
                        java.math.BigDecimal.valueOf(
                            item.getQuantity()
                        )
                    )
            )
            """
    )
    CartItemResponse toResponse(CartItem item);
}