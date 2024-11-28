package org.example.ecommerce.productinventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ProductInventoryRepository extends JpaRepository<ProductInventoryEntity, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE ProductInventoryEntity p SET p.quantity = :quantity, p.updatedAt = CURRENT_TIMESTAMP WHERE p.id = :id")
    int updateProductQuantity(UUID id, long quantity);
}
