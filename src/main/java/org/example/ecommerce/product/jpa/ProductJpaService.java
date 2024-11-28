package org.example.ecommerce.product.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.product.Product;
import org.example.ecommerce.product.ProductWithCategory;
import org.example.ecommerce.subcategory.jpa.SubCategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductJpaService {
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;

    public List<Product> getAll() {
        log.info("Requesting all products");
        return productRepository.findAll().stream().map(Product::from).toList();
    }

    public List<ProductWithCategory> getAllWithCategories() {
        log.info("Requesting all products with categories");
        return productRepository.findAllWithCategories().stream().map(ProductWithCategory::from).toList();
    }

    public Optional<Product> getProduct(UUID id) {
        log.info("Requesting product with id {}", kv("id", id));
        return productRepository.findById(id).map(Product::from);
    }

    public Optional<ProductWithCategory> getProductWithCategory(UUID id) {
        log.info("Requesting product and category with id {}", kv("id", id));
        return productRepository.findProductEntityWithSubCategory(id).map(ProductWithCategory::from);
    }

    public UUID createProduct(String name, @Nullable String description, @Nullable String imageUrl, UUID subCategoryId) {
        try {
            var subCategoryEntity = subCategoryRepository.getReferenceById(subCategoryId);
            var product = new ProductEntity();
            product.setName(name);
            product.setDescription(description);
            product.setImageUrl(imageUrl);
            product.setSubCategoryEntity(subCategoryEntity);
            var savedEntity = productRepository.save(product);
            return savedEntity.getId();
        } catch (DataIntegrityViolationException ex) {
            throw new NotFoundException("Parent sub-category is not found.");
        }

    }
}
