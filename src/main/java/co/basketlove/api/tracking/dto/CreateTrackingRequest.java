package co.basketlove.api.tracking.dto;

import co.basketlove.api.tracking.enums.TrackingStatus;

public record CreateTrackingRequest(

        Long orderId,

        TrackingStatus status,

        String description
) {
}