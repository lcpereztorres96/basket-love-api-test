package co.basketlove.api.products.service.impl;

import co.basketlove.api.categories.entity.Category;
import co.basketlove.api.categories.repository.CategoryRepository;
import co.basketlove.api.exception.ResourceNotFoundException;
import co.basketlove.api.products.dto.CreateProductRequest;
import co.basketlove.api.products.dto.ProductResponse;
import co.basketlove.api.products.entity.Product;
import co.basketlove.api.products.mapper.ProductMapper;
import co.basketlove.api.products.repository.ProductRepository;
import co.basketlove.api.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    @Override
    public ProductResponse create(CreateProductRequest request) {

        Category category = categoryRepository
                .findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        Product product = new Product();

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setImageUrl(request.imageUrl());
        product.setStock(request.stock());
        product.setCategory(category);

        repository.save(product);

        return mapper.toResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> findAll(
            String search,
            Long categoryId,
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<Product> products;

        if (search != null && !search.isBlank()) {

            products = repository.findByNameContainingIgnoreCase(
                    search,
                    pageable
            );

        } else if (categoryId != null) {

            products = repository.findByCategoryId(
                    categoryId,
                    pageable
            );

        } else {

            products = repository.findAll(pageable);
        }

        return products.map(mapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {

        Product product = repository
                .findDetailedById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"
                        ));

        return mapper.toResponse(product);
    }
}