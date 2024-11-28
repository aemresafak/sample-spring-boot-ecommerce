package org.example.ecommerce.subcategory;

import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.category.Category;
import org.example.ecommerce.subcategory.jpa.SubCategoryEntity;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Slf4j
public record SubCategory(UUID id, String name, @Nullable String description, Category category) {
    public static SubCategory from(SubCategoryEntity subCategoryEntity) {
        var category = Category.from(subCategoryEntity.getCategory());
        return new SubCategory(subCategoryEntity.getId(), subCategoryEntity.getName(), subCategoryEntity.getDescription(), category);
    }
}
