package co.basketlove.api.tracking.service;

import co.basketlove.api.tracking.dto.CreateTrackingRequest;
import co.basketlove.api.tracking.dto.TrackingResponse;

import java.util.List;

public interface TrackingService {

    TrackingResponse create(
            CreateTrackingRequest request
    );

    List<TrackingResponse> findByOrder(
            Long orderId
    );
}