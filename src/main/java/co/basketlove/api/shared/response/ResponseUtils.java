package co.basketlove.api.shared.response;

public class ResponseUtils {

    private ResponseUtils() {
    }

    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {

        return new ApiResponse<>(
                true,
                message,
                data
        );
    }
}