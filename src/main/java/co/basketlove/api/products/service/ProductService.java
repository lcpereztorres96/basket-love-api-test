package co.basketlove.api.products.service;

import co.basketlove.api.products.dto.CreateProductRequest;
import co.basketlove.api.products.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse create(CreateProductRequest request);

    Page<ProductResponse> findAll(
            String search,
            Long categoryId,
            int page,
            int size,
            String sortBy,
            String direction
    );

    ProductResponse findById(Long id);
}