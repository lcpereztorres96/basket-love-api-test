package co.basketlove.api.personalization.service;

import co.basketlove.api.personalization.dto.CreatePersonalizationRequest;
import co.basketlove.api.personalization.dto.PersonalizationResponse;

public interface PersonalizationService {

    PersonalizationResponse create(
            CreatePersonalizationRequest request
    );
}