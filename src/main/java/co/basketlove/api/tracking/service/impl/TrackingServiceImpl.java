package co.basketlove.api.tracking.service.impl;

import co.basketlove.api.exception.ResourceNotFoundException;
import co.basketlove.api.orders.entity.Order;
import co.basketlove.api.orders.repository.OrderRepository;
import co.basketlove.api.tracking.dto.CreateTrackingRequest;
import co.basketlove.api.tracking.dto.TrackingResponse;
import co.basketlove.api.tracking.entity.OrderTracking;
import co.basketlove.api.tracking.repository.OrderTrackingRepository;
import co.basketlove.api.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl
        implements TrackingService {

    private final OrderRepository orderRepository;
    private final OrderTrackingRepository repository;

    @Override
    @Transactional
    public TrackingResponse create(
            CreateTrackingRequest request
    ) {

        Order order = orderRepository
                .findById(request.orderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"
                        ));

        OrderTracking tracking = new OrderTracking();

        tracking.setOrder(order);
        tracking.setStatus(request.status());
        tracking.setDescription(
                request.description()
        );

        repository.save(tracking);

        return new TrackingResponse(
                tracking.getId(),
                tracking.getStatus(),
                tracking.getDescription(),
                tracking.getCreatedAt()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrackingResponse> findByOrder(
            Long orderId
    ) {

        return repository
                .findByOrderIdOrderByCreatedAtAsc(orderId)
                .stream()
                .map(tracking -> new TrackingResponse(
                        tracking.getId(),
                        tracking.getStatus(),
                        tracking.getDescription(),
                        tracking.getCreatedAt()
                ))
                .toList();
    }
}