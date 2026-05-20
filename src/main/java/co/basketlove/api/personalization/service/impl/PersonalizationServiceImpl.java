package co.basketlove.api.personalization.service.impl;

import co.basketlove.api.exception.ResourceNotFoundException;
import co.basketlove.api.orders.entity.Order;
import co.basketlove.api.orders.repository.OrderRepository;
import co.basketlove.api.personalization.dto.CreatePersonalizationRequest;
import co.basketlove.api.personalization.dto.PersonalizationResponse;
import co.basketlove.api.personalization.entity.OrderPersonalization;
import co.basketlove.api.personalization.repository.OrderPersonalizationRepository;
import co.basketlove.api.personalization.service.PersonalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonalizationServiceImpl
        implements PersonalizationService {

    private final OrderRepository orderRepository;
    private final OrderPersonalizationRepository repository;

    @Override
    @Transactional
    public PersonalizationResponse create(
            CreatePersonalizationRequest request
    ) {

        Order order = orderRepository
                .findById(request.orderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"
                        ));

        OrderPersonalization personalization =
                new OrderPersonalization();

        personalization.setOrder(order);
        personalization.setMessage(request.message());
        personalization.setRecipientName(
                request.recipientName()
        );
        personalization.setSenderName(
                request.senderName()
        );
        personalization.setAnonymousSender(
                request.anonymousSender()
        );
        personalization.setCardStyle(
                request.cardStyle()
        );

        repository.save(personalization);

        return new PersonalizationResponse(
                personalization.getId(),
                order.getId(),
                personalization.getMessage(),
                personalization.getRecipientName(),
                personalization.getSenderName(),
                personalization.isAnonymousSender(),
                personalization.getCardStyle()
        );
    }
}