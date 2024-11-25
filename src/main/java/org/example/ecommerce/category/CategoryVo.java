package org.example.ecommerce.category;

import org.springframework.lang.Nullable;

public record CategoryVo(Integer id, String name, @Nullable String description) {
    public static CategoryVo from(Category category) {
        return new CategoryVo(category.getId(), category.getName(), category.getDescription());
    }
}