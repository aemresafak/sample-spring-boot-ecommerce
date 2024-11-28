package org.example.ecommerce.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.product.Product;
import org.example.ecommerce.product.jpa.ProductJpaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductJpaService productJpaService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productJpaService.getAll();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable UUID productId) {
        return productJpaService.getProduct(productId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public CreateProductResponse createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        var id = productJpaService.createProduct(createProductRequest.name(), createProductRequest.description(), createProductRequest.imageUrl(), createProductRequest.subCategoryId());
        return new CreateProductResponse(id);
    }

}
