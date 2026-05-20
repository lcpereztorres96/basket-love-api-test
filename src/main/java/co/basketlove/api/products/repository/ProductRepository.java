package co.basketlove.api.products.repository;

import co.basketlove.api.products.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "category")
    Page<Product> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    @EntityGraph(attributePaths = "category")
    Page<Product> findByCategoryId(
            Long categoryId,
            Pageable pageable
    );

    @Override
    @EntityGraph(attributePaths = "category")
    Page<Product> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "category")
    Optional<Product> findDetailedById(Long id);
}