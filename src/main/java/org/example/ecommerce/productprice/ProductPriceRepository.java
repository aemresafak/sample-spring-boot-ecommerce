package org.example.ecommerce.productprice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, UUID> {
    Optional<ProductPriceEntity> findByProductIdAndDeletedFalse(UUID productId);
}
