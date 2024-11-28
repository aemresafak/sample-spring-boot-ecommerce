package org.example.ecommerce.productprice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, UUID> {
    Optional<ProductPriceEntity> findByProductIdAndDeletedFalse(UUID productId);

    @Query("UPDATE ProductPriceEntity p SET p.deleted = true WHERE p.id = :uuid")
    @Modifying
    @Transactional
    int softDeleteById(UUID uuid);
}
