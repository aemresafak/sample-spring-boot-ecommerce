package org.example.ecommerce.product;

import org.example.ecommerce.product.jpa.ProductEntity;
import org.example.ecommerce.subcategory.SubCategory;

public record ProductWithCategory(Product product, SubCategory subCategory) {
    public static ProductWithCategory from(ProductEntity entity) {
        return new ProductWithCategory(Product.from(entity), SubCategory.from(entity.getSubCategoryEntity()));
    }
}
