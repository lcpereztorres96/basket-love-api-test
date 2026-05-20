package co.basketlove.api.personalization.dto;

public record PersonalizationResponse(

        Long id,

        Long orderId,

        String message,

        String recipientName,

        String senderName,

        boolean anonymousSender,

        String cardStyle
) {
}