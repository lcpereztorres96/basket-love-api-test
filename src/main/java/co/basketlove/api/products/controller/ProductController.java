package co.basketlove.api.products.controller;

import co.basketlove.api.products.dto.CreateProductRequest;
import co.basketlove.api.products.dto.ProductResponse;
import co.basketlove.api.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductResponse create(
            @Valid @RequestBody CreateProductRequest request
    ) {

        return service.create(request);
    }

    @GetMapping
    public Page<ProductResponse> findAll(

            @RequestParam(required = false)
            String search,

            @RequestParam(required = false)
            Long categoryId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "createdAt")
            String sortBy,

            @RequestParam(defaultValue = "desc")
            String direction
    ) {

        return service.findAll(
                search,
                categoryId,
                page,
                size,
                sortBy,
                direction
        );
    }

    @GetMapping("/{id}")
    public ProductResponse findById(
            @PathVariable Long id
    ) {

        return service.findById(id);
    }
}