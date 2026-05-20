package co.basketlove.api.shared.response;

public record ApiResponse<T>(

        boolean success,

        String message,

        T data
) {
}