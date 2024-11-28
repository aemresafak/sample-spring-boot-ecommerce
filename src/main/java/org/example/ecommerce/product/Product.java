package org.example.ecommerce.product;

import org.example.ecommerce.product.jpa.ProductEntity;
import org.springframework.lang.Nullable;

import java.util.UUID;

public record Product(UUID id, String name, @Nullable String description, @Nullable String imageUrl) {
    public static Product from(ProductEntity entity) {
        return new Product(entity.getId(), entity.getName(), entity.getDescription(), entity.getImageUrl());
    }
}
