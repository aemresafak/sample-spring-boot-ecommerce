package org.example.ecommerce.category;

import org.springframework.lang.Nullable;

import java.util.UUID;

public record Category(UUID id, String name, @Nullable String description) {
    public static Category from(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
    }
}