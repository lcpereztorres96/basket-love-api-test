package co.basketlove.api.personalization.dto;

public record CreatePersonalizationRequest(

        Long orderId,

        String message,

        String recipientName,

        String senderName,

        boolean anonymousSender,

        String cardStyle
) {
}