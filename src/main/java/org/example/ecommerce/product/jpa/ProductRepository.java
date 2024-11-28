package org.example.ecommerce.product.jpa;

import org.example.ecommerce.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.subCategoryEntity where p.id = :productId")
    Optional<ProductEntity> findProductEntityWithSubCategory(UUID productId);

    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.subCategoryEntity")
    List<ProductEntity> findAllWithCategories();
}
