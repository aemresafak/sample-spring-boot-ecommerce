package org.example.ecommerce.productinventory;

import java.util.UUID;

public record ProductInventory(UUID productId, long quantity) {
    public static ProductInventory from(ProductInventoryEntity entity) {
        return new ProductInventory(entity.getId(), entity.getQuantity());
    }
}
