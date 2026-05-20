package co.basketlove.api.categories.service.impl;

import co.basketlove.api.categories.dto.CategoryResponse;
import co.basketlove.api.categories.dto.CreateCategoryRequest;
import co.basketlove.api.categories.entity.Category;
import co.basketlove.api.categories.mapper.CategoryMapper;
import co.basketlove.api.categories.repository.CategoryRepository;
import co.basketlove.api.categories.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponse create(CreateCategoryRequest request) {

        Category category = new Category();

        category.setName(request.name());
        category.setImageUrl(request.imageUrl());

        repository.save(category);

        return mapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
}