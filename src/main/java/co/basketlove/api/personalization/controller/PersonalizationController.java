package co.basketlove.api.personalization.controller;

import co.basketlove.api.personalization.dto.CreatePersonalizationRequest;
import co.basketlove.api.personalization.dto.PersonalizationResponse;
import co.basketlove.api.personalization.service.PersonalizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/personalizations")
@RequiredArgsConstructor
public class PersonalizationController {

    private final PersonalizationService service;

    @PostMapping
    public PersonalizationResponse create(
            @RequestBody
            CreatePersonalizationRequest request
    ) {

        return service.create(request);
    }
}