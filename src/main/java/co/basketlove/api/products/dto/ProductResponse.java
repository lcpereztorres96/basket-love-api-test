package co.basketlove.api.products.dto;

import java.math.BigDecimal;

public record ProductResponse(

        Long id,

        String name,

        String description,

        BigDecimal price,

        String imageUrl,

        Integer stock,

        boolean active,

        Long categoryId,

        String categoryName
) {
}