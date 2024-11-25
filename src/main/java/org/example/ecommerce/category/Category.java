package org.example.ecommerce.category;

import org.springframework.lang.Nullable;

public record Category(Integer id, String name, @Nullable String description) {
    public static Category from(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getDescription());
    }
}