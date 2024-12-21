package org.example.ecommerce.product.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.product.ProductWithCategory;
import org.example.ecommerce.product.jpa.ProductJpaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/products-with-categories")
@RequiredArgsConstructor
@PreAuthorize(value = "hasRole('ADMIN')")
public class ProductWithCategoryController {
    private final ProductJpaService productJpaService;

    @GetMapping
    public List<ProductWithCategory> getAllProducts() {
        return productJpaService.getAllWithCategories();
    }

    @GetMapping("/{productId}")
    public ProductWithCategory getProduct(@PathVariable UUID productId) {
        return productJpaService.getProductWithCategory(productId).orElseThrow(NotFoundException::new);
    }
}
