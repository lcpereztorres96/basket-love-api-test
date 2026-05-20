package co.basketlove.api.users.dto;

import co.basketlove.api.users.enums.UserRole;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        UserRole role
) {
}