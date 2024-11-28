package org.example.ecommerce.productinventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductInventoryJpaService {
    private final ProductInventoryRepository productInventoryRepository;

    public Optional<ProductInventory> findById(UUID id) {
        log.info("Fetching product inventory by {}",kv("id", id));
        return productInventoryRepository.findById(id).map(ProductInventory::from);
    }

    public void createOrUpdateInventory(UUID productId, long quantity) {
        log.info("Creating inventory {} {}",kv("productId",productId), kv("quantity",quantity));
        var productInventory = new ProductInventoryEntity();
        productInventory.setId(productId);
        productInventory.setQuantity(quantity);
        productInventoryRepository.save(productInventory);
    }
}
