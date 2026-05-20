package co.basketlove.api.tracking.dto;

import co.basketlove.api.tracking.enums.TrackingStatus;

import java.time.LocalDateTime;

public record TrackingResponse(

        Long id,

        TrackingStatus status,

        String description,

        LocalDateTime createdAt
) {
}