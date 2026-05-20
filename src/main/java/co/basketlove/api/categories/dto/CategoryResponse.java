package co.basketlove.api.categories.dto;

public record CategoryResponse(
        Long id,
        String name,
        String imageUrl
) {
}