package org.example.ecommerce.product;

import org.example.ecommerce.product.jpa.ProductEntity;
import org.example.ecommerce.productprice.ProductPrice;
import org.springframework.lang.Nullable;

import java.util.UUID;

public record Product(UUID id, String name, ProductPrice productPrice,
                      @Nullable String description, @Nullable String imageUrl) {
    public static Product from(ProductEntity entity) {
        var productPrice = ProductPrice.from(entity.getPrice());
        return new Product(entity.getId(), entity.getName(), productPrice, entity.getDescription(), entity.getImageUrl());
    }
}
