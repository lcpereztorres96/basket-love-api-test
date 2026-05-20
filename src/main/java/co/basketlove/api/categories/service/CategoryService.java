package co.basketlove.api.categories.service;

import co.basketlove.api.categories.dto.CategoryResponse;
import co.basketlove.api.categories.dto.CreateCategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CreateCategoryRequest request);

    List<CategoryResponse> findAll();
}