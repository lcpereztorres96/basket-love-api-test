package co.basketlove.api.categories.controller;

import co.basketlove.api.categories.dto.CategoryResponse;
import co.basketlove.api.categories.dto.CreateCategoryRequest;
import co.basketlove.api.categories.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public CategoryResponse create(
            @Valid @RequestBody CreateCategoryRequest request
    ) {

        return service.create(request);
    }

    @GetMapping
    public List<CategoryResponse> findAll() {

        return service.findAll();
    }
}