package co.basketlove.api.tracking.controller;

import co.basketlove.api.tracking.dto.CreateTrackingRequest;
import co.basketlove.api.tracking.dto.TrackingResponse;
import co.basketlove.api.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService service;

    @PostMapping
    public TrackingResponse create(
            @RequestBody
            CreateTrackingRequest request
    ) {

        return service.create(request);
    }

    @GetMapping("/{orderId}")
    public List<TrackingResponse> findByOrder(
            @PathVariable Long orderId
    ) {

        return service.findByOrder(orderId);
    }
}